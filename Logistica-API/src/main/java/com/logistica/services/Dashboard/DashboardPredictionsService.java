package com.logistica.services.Dashboard;

import com.configuration.Exception.UserFriendlyException;
import com.configuration.TenantContext;
import com.logistica.dtos.ItemOfPredSeries;
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
    public CompletableFuture<List<ItemOfPredSeries>> getChart(Map<String, String> params) throws UserFriendlyException {
        var filters = Arrays.asList("products", "clients", "suppliers");
        var filter = params.get("filter");
        if (!filters.contains(filter)) throw new UserFriendlyException("error in your filter");

        Session session = entityManager.unwrap(Session.class);
        var list = new ArrayList<ItemOfPredSeries>();
        List<Object[]> query = session.createSQLQuery("select od.product_id, o.createdAt as time, sum(od.qte) " +
                "from output o " +
                "inner join outputdetails od on o.id = od.output_id " +
                "where o.tenantId = :tenantId and o.deletedAt is null and od.product_id=1 " +
                "group by od.product_id, time " +
                "order by od.product_id, time")
                .setParameter("tenantId", TenantContext.getCurrentTenant()).list();
        for (var row : query) {
            list.add(new ItemOfPredSeries(((Date) row[1]), ((Number) row[2]).doubleValue()));
        }
        return CompletableFuture.completedFuture(list);
    }

}
