package com.logistica.services.Dashboard;

import com.configuration.Exception.UserFriendlyException;
import com.configuration.TenantContext;
import com.logistica.dtos.ItemOfPredSeries;
import com.logistica.dtos.ListOfPredSeries;
import com.logistica.repositories.Products.IInputRepository;
import com.logistica.repositories.Products.IOutputRepository;
import com.logistica.repositories.Products.IProductRepository;
import com.logistica.repositories.Products.ITransferRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class DashboardPredictionsService implements IDashboardPredictionsService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private IInputRepository iInputRepository;
    @Autowired
    private IOutputRepository iOutputRepository;
    @Autowired
    private ITransferRepository iTransferRepository;

    @Autowired
    private IProductRepository iProductRepository;

    //todo: should be optimized :)
    public CompletableFuture<ListOfPredSeries> getChart(Map<String, String> params) throws UserFriendlyException {
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
        for (var input : inputs) {
            var out = outputs.stream().filter(o -> o[0].toString().equals(input[0].toString())).collect(Collectors.toList());
            if (out.size() > 0) {
                var first = out.get(0);
                rest.add(new ItemOfPredSeries(((Date) input[0]), ((Number) input[1]).doubleValue() - ((Number) first[1]).doubleValue()));
            } else
                rest.add(new ItemOfPredSeries(((Date) input[0]), ((Number) input[1]).doubleValue()));
        }
        for (var output : outputs) {
            var in = inputs.stream().filter(o -> o[0].toString().equals(output[0].toString())).collect(Collectors.toList());
            if (in.size() == 0) {
                rest.add(new ItemOfPredSeries(((Date) output[0]), ((Number) output[1]).doubleValue()));
            }
        }
        rest = rest.stream().sorted((o1, o2) -> o1.getTime().compareTo(o2.getTime())).collect(Collectors.toList());
        //todo: optimize this one
        double[] tmp = rest.stream().mapToDouble(ItemOfPredSeries::getValue).toArray();
        Arrays.parallelPrefix(tmp, (x, y) -> x + y);

        var prod = iProductRepository.findById(1L).get();
        result.setMax(Long.valueOf(prod.getStockMax()));
        result.setMed(Long.valueOf(prod.getStockSecurity()));
        result.setMin(Long.valueOf(prod.getStockMin()));
        result.setItems(rest);
        return CompletableFuture.completedFuture(result);
    }

}
