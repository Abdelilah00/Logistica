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

    //get top 10 profitable products
    public CompletableFuture<ListOfPredSeries> getChart(Map<String, String> params) throws UserFriendlyException {
        var filters = Arrays.asList("products", "clients", "suppliers");
        var filter = params.get("filter");
        if (!filters.contains(filter)) throw new UserFriendlyException("error in your filter");

        Session session = entityManager.unwrap(Session.class);
        ListOfPredSeries result = new ListOfPredSeries();

        List<Object[]> inputs = session.createSQLQuery("select DATE(i.createdAt) time, SUM(id.qte) qte " +
                "from input i " +
                "inner join inputdetails id on i.id = id.input_id " +
                "where id.product_id = 1 and a.tenantId = :tenantId and a.deletedAt is null " +
                "group by time")
                .setParameter("tenantId", TenantContext.getCurrentTenant()).list();
        List<Object[]> outputs = session.createSQLQuery("select DATE(o.createdAt) time, SUM(id.qte) qte " +
                "from output o " +
                "inner join outputdetails id on o.id = id.output_id " +
                "where id.product_id = 1 and a.tenantId = :tenantId and a.deletedAt is null " +
                "group by timee")
                .setParameter("tenantId", TenantContext.getCurrentTenant()).list();

        List<ItemOfPredSeries> rest = new ArrayList<>();
        for (var input : inputs)
            outputs.


        //todo: optimize this one
        double[] tmp = query.stream().mapToDouble(x -> ((Number) x[1]).doubleValue()).toArray();
        Arrays.parallelPrefix(tmp, (x, y) -> x + y);
        for (int i = 0; i < query.size(); i++) {
            result.getItems().add(new ItemOfPredSeries(((Date) query.get(i)[0]), tmp[i]));
        }

        result.setMax(((Number) (query.get(query.size() - 1)[2])).longValue());
        result.setMed(((Number) (query.get(query.size() - 1)[3])).longValue());
        result.setMin(((Number) (query.get(query.size() - 1)[4])).longValue());
        return CompletableFuture.completedFuture(result);
    }

}
