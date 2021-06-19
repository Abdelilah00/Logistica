package com.logistica.services.Dashboard;

import com.configuration.Exception.UserFriendlyException;
import com.configuration.TenantContext;
import com.logistica.dtos.TreeMapItemDto;
import com.logistica.repositories.Products.IInputRepository;
import com.logistica.repositories.Products.IOutputRepository;
import com.logistica.repositories.Products.IProductRepository;
import com.logistica.repositories.Products.ITransferRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
    public CompletableFuture<List<TreeMapItemDto>> getChart(Map<String, String> params) throws UserFriendlyException {
        var filters = Arrays.asList("products", "clients", "suppliers");
        int n = Integer.valueOf(params.get("n"));
        var filter = params.get("filter");
        if (!filters.contains(filter)) throw new UserFriendlyException("error in your filter");

        Session session = entityManager.unwrap(Session.class);
        var list = new ArrayList<TreeMapItemDto>();
        List<Object[]> query = session.createSQLQuery("select p.name, SUM(od.qte * od.priceHT - od.qte * p.priceHT) as profit " +
                "from output o" +
                "         inner join outputdetails od on o.id = od.output_id " +
                "         inner join product p on od.product_id = p.id " +
                "         inner join inputdetails id on p.id = id.product_id " +
                "where o.intern = FALSE " +
                "and o.tenantId = :tenantId and o.deletedAt is null " +
                "group by p.id " +
                "having profit != 0 " +
                "order by profit desc " +
                "limit :max").setParameter("max", n).setParameter("tenantId", TenantContext.getCurrentTenant()).list();
        for (var row : query) {
            list.add(new TreeMapItemDto(row[0].toString(), ((Number) row[1]).longValue(), null));
        }
        return CompletableFuture.completedFuture(list);
    }

}
