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
        var productId = params.get("productId");
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

        Map<Date, Double> inputMap = new HashMap<>();
        for (var input : inputs)
            inputMap.put(new SimpleDateFormat("yyyy-MM-dd").parse(input[0].toString()), ((Number) input[1]).doubleValue());

        Map<Date, Double> outputMap = new HashMap<>();
        for (var output : outputs)
            outputMap.put(new SimpleDateFormat("yyyy-MM-dd").parse(output[0].toString()), ((Number) output[1]).doubleValue());

        var d3 = new SimpleDateFormat("yyyy-MM-dd").parse("2018-11-22");
        var d1 = new SimpleDateFormat("yyyy-MM-dd").parse("2018-01-25");
        var d2 = new SimpleDateFormat("yyyy-MM-dd").parse("2019-03-03");
        var g = inputMap.get(d1);
        var gg = inputMap.get(d2);
        var ggg = inputMap.get(d3);

        var dd1 = new SimpleDateFormat("yyyy-MM-dd").parse("2018-04-07");
        var dd2 = new SimpleDateFormat("yyyy-MM-dd").parse("2017-11-01");
        var dg = outputMap.get(dd1);
        var dgg = outputMap.get(dd2);


        while (tmpDate.compareTo(currDate) < 0) {
            var input = inputMap.get(tmpDate) == null ? 0 : inputMap.get(tmpDate);
            var output = outputMap.get(tmpDate) == null ? 0 : outputMap.get(tmpDate);
            qteCumule = input - output + qteCumule;

            rest.add(new ItemOfPredSeries(tmpDate, qteCumule));
            tmpDate = addDays(tmpDate, 1);
        }

        rest = rest.stream().sorted((o1, o2) -> o1.getTime().compareTo(o2.getTime())).collect(Collectors.toList());

        //var simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String jsonReal = gson.toJson(rest);
        /*        FileWriter myWriter = new FileWriter("D:\\Result.json", true);
        myWriter.write(json);
        myWriter.close();*/

        var response = webClient.baseUrl("http://localhost:5000/").build()
                .post().uri("predict")
                .accept(MediaType.ALL).contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromFormData("series", jsonReal))
                .retrieve().bodyToMono(String.class).block();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode nodes = objectMapper.readTree(response);
        var dataNode = nodes.get("data").toString();

        PredResult[] flaskResponse = objectMapper.readValue(dataNode, PredResult[].class);

        var prod = iProductRepository.findById(Long.valueOf(productId)).get();
        result.setMax(Long.valueOf(prod.getStockMax()));
        result.setMed(Long.valueOf(prod.getStockSecurity()));
        result.setMin(Long.valueOf(prod.getStockMin()));
        result.setItems(rest);

        for (int i = 0; i < flaskResponse.length; i++) {
            result.getPredItems().add(new ItemOfPredSeries(flaskResponse[i].getIndex(), flaskResponse[i].getPredicted_mean()));
        }

        return CompletableFuture.completedFuture(result);
    }

    private static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);// w ww.  j ava  2  s  .co m
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return new Date(cal.getTime().getTime());

    }
}
