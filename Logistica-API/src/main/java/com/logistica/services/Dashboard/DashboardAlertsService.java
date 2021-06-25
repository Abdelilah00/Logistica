package com.logistica.services.Dashboard;

import com.configuration.Exception.UserFriendlyException;
import com.configuration.TenantContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.logistica.domains.Products.Product;
import com.logistica.dtos.Dashboard.Alerts.AlertsItem;
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
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class DashboardAlertsService implements IDashboardAlertsService {

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

    @Autowired
    private IDashboardPredictionsService iDashboardPredictionsService;

    private static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);// w ww.  j ava  2  s  .co m
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return new Date(cal.getTime().getTime());
    }

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

        while (tmpDate.compareTo(currDate) < 0) {
            Date finalTmpDate = tmpDate;
            Object[] input = inputs.stream().filter(carnet -> ((Date) carnet[0]).compareTo(finalTmpDate) == 0).findFirst().orElse(null);
            Object[] output = outputs.stream().filter(carnet -> ((Date) carnet[0]).compareTo(finalTmpDate) == 0).findFirst().orElse(null);

            if (input != null && output != null)
                rest.add(new ItemOfPredSeries(((Date) input[0]), ((Number) input[1]).doubleValue() - ((Number) output[1]).doubleValue()));
            else if (input != null && output == null)
                rest.add(new ItemOfPredSeries(((Date) input[0]), ((Number) input[1]).doubleValue()));
            else if (output != null)
                rest.add(new ItemOfPredSeries(((Date) output[0]), ((Number) output[1]).doubleValue()));
            else if (input == null && output == null) {
                //find by previous day
                double x = rest.stream().filter(carnet -> carnet.getTime().compareTo(addDays(finalTmpDate, -1)) == 0).findFirst().orElse(new ItemOfPredSeries()).getValue();
                rest.add(new ItemOfPredSeries(finalTmpDate, x));
            }
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

    @Override
    public CompletableFuture<List<AlertsItem>> getRealQte(Map<String, String> params) throws UserFriendlyException, IOException, ParseException {
        Session session = entityManager.unwrap(Session.class);

        List<Object[]> inputObjects = session.createSQLQuery("select p.name,p.stockMax,p.stockMin, SUM(id.qte) qte " +
                "from input i " +
                "inner join inputdetails id on i.id = id.input_id inner join product p on p.id=id.product_id " +
                "where i.tenantId = :tenantId and i.deletedAt is null " +
                "group by id.product_id")
                .setParameter("tenantId", TenantContext.getCurrentTenant()).list();

        List<Object[]> outputObjects = session.createSQLQuery("select p.name ,p.stockMax ,p.stockMin, SUM(od.qte) qte " +
                "from output o " +
                "inner join outputdetails od on o.id = od.output_id inner join product p on p.id=od.product_id " +
                "where  o.tenantId = :tenantId and o.deletedAt is null " +
                "group by p.id")
                .setParameter("tenantId", TenantContext.getCurrentTenant()).list();

        Map<String, AlertsItem> inputsMap = new HashMap<>();
        for (Object[] o : inputObjects) {
            inputsMap.put(o[0].toString(), AlertsItem.builder().name(o[0].toString()).max(((Number) o[1]).longValue()).min(((Number) o[2]).longValue()).acc(((Number) o[3]).longValue()).build());
        }
        Map<String, AlertsItem> outputsMap = new HashMap<>();
        for (Object[] o : outputObjects) {
            outputsMap.put(o[0].toString(), AlertsItem.builder().name(o[0].toString()).max(((Number) o[1]).longValue()).min(((Number) o[2]).longValue()).acc(((Number) o[3]).longValue()).build());
        }

        outputsMap.forEach(
                (key, value) -> inputsMap.merge(key, value, (v1, v2) -> AlertsItem.builder().name(v1.getName()).max(v1.getMax()).min(v1.getMin()).acc(v1.getAcc() - v2.getAcc()).build())
        );

        List<AlertsItem> result = new ArrayList<>(inputsMap.values());
        return CompletableFuture.completedFuture(result);
    }

    @Override
    public CompletableFuture<List<AlertsItem>> getPredQte(Map<String, String> params) throws UserFriendlyException, IOException, ExecutionException, InterruptedException {
        Session session = entityManager.unwrap(Session.class);
        List<Object[]> productObjects = session.createQuery("select distinct p.id,p.stockMax,p.stockMin,p.name from Product p inner join p.inputDetails").list();
        var products = new ArrayList<Product>();

        for (var o : productObjects) {
            var prod = new Product();
            prod.setId(((Number) o[0]).longValue());
            prod.setStockMax(((Number) o[1]).intValue());
            prod.setStockMin(((Number) o[2]).intValue());
            prod.setName(o[3].toString());
            products.add(prod);
        }

        List<AlertsItem> result = new ArrayList<>();

        for (var product : products) {
            Map<String, String> tmpParams = new HashMap<>();
            tmpParams.put("forecast", "2");
            tmpParams.put("productId", String.valueOf(product.getId()));
            var predQteOfProduct = iDashboardPredictionsService.forecast(tmpParams).get().getPredItems().get(0).getValue();
            result.add(AlertsItem.builder().acc(predQteOfProduct.longValue()).max(product.getStockMax().longValue()).min(product.getStockMin().longValue()).name(product.getName()).build());
        }

        return CompletableFuture.completedFuture(result);
    }
}
