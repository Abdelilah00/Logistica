package com.logistica.services.Dashboard;

import com.configuration.Exception.UserFriendlyException;
import com.configuration.TenantContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.logistica.dtos.Dashboard.Predictions.ItemOfPredSeries;
import com.logistica.dtos.Dashboard.Predictions.ListOfPredSeries;
import com.logistica.repositories.Products.IInputRepository;
import com.logistica.repositories.Products.IOutputRepository;
import com.logistica.repositories.Products.IProductRepository;
import com.logistica.repositories.Products.ITransferRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class DashboardPredictionsService implements IDashboardPredictionsService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    @Qualifier("getWebClientBuilder")
    private WebClient.Builder webClient;

    @Autowired
    private IInputRepository iInputRepository;
    @Autowired
    private IOutputRepository iOutputRepository;
    @Autowired
    private ITransferRepository iTransferRepository;

    @Autowired
    private IProductRepository iProductRepository;

    //todo: should be optimized :)
    public CompletableFuture<ListOfPredSeries> getChart(Map<String, String> params) throws UserFriendlyException, IOException, ParseException {
        return forecast(params);
    }

    public CompletableFuture<ListOfPredSeries> forecast(Map<String, String> params) throws UserFriendlyException, IOException {
        var productId = params.get("productId");
        var forecast = params.get("forecast") == null ? "365" : params.get("forecast");
        if (productId == null) throw new UserFriendlyException("error in your filter");

        Session session = entityManager.unwrap(Session.class);
        ListOfPredSeries result = new ListOfPredSeries();

        List<Object[]> inputs = session.createSQLQuery("select DATE(i.createdAt) time, SUM(id.qte) qte " +
                "from input i " +
                "inner join inputdetails id on i.id = id.input_id " +
                "where id.product_id = :productId and i.tenantId = :tenantId and i.deletedAt is null " +
                "group by time")
                .setParameter("tenantId", TenantContext.getCurrentTenant()).setParameter("productId", productId).list();
        List<Object[]> outputs = session.createSQLQuery("select DATE(o.createdAt) time, SUM(id.qte) qte " +
                "from output o " +
                "inner join outputdetails id on o.id = id.output_id " +
                "where id.product_id = :productId and o.tenantId = :tenantId and o.deletedAt is null " +
                "group by time")
                .setParameter("tenantId", TenantContext.getCurrentTenant()).setParameter("productId", productId).list();

        List<ItemOfPredSeries> rest = new ArrayList<>();
        //we should reindex by date (create messing date with previous value as data)
        Date minInput = inputs.stream().map(u -> (Date) u[0]).min(Date::compareTo).get();
        Date minOutput = outputs.stream().map(u -> (Date) u[0]).min(Date::compareTo).get();
        Date minDate = minInput.compareTo(minOutput) < 0 ? minInput : minOutput;
        Date currDate = new Timestamp(new Date().getTime());
        Date tmpDate = minDate;
        double qteCumule = 0d;

        Map<String, Double> inputMap = new HashMap<>();
        for (var input : inputs)
            inputMap.put(input[0].toString(), ((Number) input[1]).doubleValue());

        Map<String, Double> outputMap = new HashMap<>();
        for (var output : outputs)
            outputMap.put(output[0].toString(), ((Number) output[1]).doubleValue());


        while (tmpDate.compareTo(currDate) < 0) {
            String t = new SimpleDateFormat("yyyy-MM-dd").format(tmpDate);
            var input = inputMap.get(t) == null ? 0 : inputMap.get(t);
            var output = outputMap.get(t) == null ? 0 : outputMap.get(t);
            var x = input - output;
            if (x != 0)
                qteCumule = x + qteCumule;

            rest.add(new ItemOfPredSeries(tmpDate, qteCumule));
            tmpDate = addDays(tmpDate, 1);
        }


        rest = rest.stream().sorted((o1, o2) -> o1.getTime().compareTo(o2.getTime())).collect(Collectors.toList());

        //var simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String jsonReal = gson.toJson(rest);
        FileWriter myWriter = new FileWriter("D:\\Result.json", true);
        myWriter.write(jsonReal);
        myWriter.close();

        var response = webClient.baseUrl("http://localhost:5000/").build()
                .post().uri("predict")
                .accept(MediaType.ALL).contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromFormData("series", jsonReal).with("forecast", forecast))
                .retrieve().bodyToMono(String.class).block();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode nodes = objectMapper.readTree(response);

        PredResult[] flaskResponse = objectMapper.readValue(nodes.get("data").toString(), PredResult[].class);
        for (int i = 0; i < flaskResponse.length; i++) {
            result.getPredItems().add(new ItemOfPredSeries(flaskResponse[i].getIndex(), flaskResponse[i].getPredicted_mean()));
        }


        var prod = iProductRepository.findById(Long.valueOf(productId)).get();
        result.setMax(Long.valueOf(prod.getStockMax()));
        result.setMed(Long.valueOf(prod.getStockSecurity()));
        result.setMin(Long.valueOf(prod.getStockMin()));
        result.setItems(rest);


        return CompletableFuture.completedFuture(result);
    }

    private static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);// w ww.  j ava  2  s  .co m
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return new Date(cal.getTime().getTime());

    }
}

