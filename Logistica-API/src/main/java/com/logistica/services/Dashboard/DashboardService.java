package com.logistica.services.Dashboard;

import com.configuration.Exception.UserFriendlyException;
import com.configuration.TenantContext;
import com.logistica.dtos.ItemOfSeries;
import com.logistica.dtos.SeriesListDto;
import com.logistica.dtos.StatisticDto;
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
public class DashboardService implements IDashboardService {

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

    @Override
    //todo : params for the hidden stat
    public CompletableFuture<List<StatisticDto>> getStatistics() {
        Session session = entityManager.unwrap(Session.class);
        var statistics = new ArrayList<StatisticDto>();
        statistics.add(new StatisticDto("INPUT_COUNT", (double) iInputRepository.count(), 0D));
        statistics.add(new StatisticDto("OUTPUT_COUNT", (double) iOutputRepository.count(), 0D));
        statistics.add(new StatisticDto("TRANSFER_COUNT", (double) iTransferRepository.count(), 0D));

        statistics.add(new StatisticDto("INPUT_CHIFFRE", (double) session.createQuery(
                "select sum(id.qte * p.priceHT) as value " +
                        "from Input i inner join i.inputDetails id inner join id.product p where i.retour=FALSE").getSingleResult(), 0D));
        statistics.add(new StatisticDto("OUTPUT_CHIFFRE", (double) session.createQuery(
                "select sum(od.qte * od.priceHT) as value " +
                        "from Output o inner join o.outputDetails od where o.intern=FALSE").getSingleResult(), 0D));
        statistics.add(new StatisticDto("PRODUCT_COUNT", (double) iProductRepository.count(), 0D));

        //chiffre in stocks
        Double chiffreStock = (double) session.createQuery("select sum((id.qte - COALESCE(od.qte,0)) * p.priceHT) " +
                "from Input i inner join i.inputDetails id inner join id.product p " +
                "left join p.outputDetails od where i.retour = FALSE").getSingleResult();
        statistics.add(new StatisticDto("STOCK_CHIFFRE", chiffreStock, 0D));

        //Average Order Value (AOV) = total revenue / number of order
        List<Double> tmp = session.createQuery("select SUM((od.priceHT * od.qte) - (p.priceHT * od.qte)) as s" +
                "  from Input i  inner join i.inputDetails id inner join id.product p inner join p.outputDetails od inner join od.output o" +
                "  where o.intern = FALSE" +
                "  group by o.id").getResultList();
        Double aov = tmp.stream().mapToDouble(d -> d).average().orElse(0.0);
        statistics.add(new StatisticDto("AOV_STATISTIC", aov, 0D));

        //Gross Profit (GP)= Total Cost of Goods Sold – Total Number of sales
        Double gp = Double.valueOf(session.createQuery("select SUM((od.priceHT * od.qte) - (p.priceHT * od.qte))" +
                "  from Input i inner join i.inputDetails id inner join id.product p inner join p.outputDetails od inner join od.output o" +
                " where o.intern = FALSE").getSingleResult().toString());
        statistics.add(new StatisticDto("GP_STATISTIC", gp, 0D));

        //Repeat Purchase Rate (RPR) = Purchases from Repeat Customers / Total Purchase
        Long o = (long) session.createQuery("select count(o.id) from Output o where intern=FALSE").getSingleResult();
        Long c = (long) session.createQuery("select count(distinct o.actor) from Output o where intern=FALSE").getSingleResult();
        Double rpr = (double) o / (double) c;
        statistics.add(new StatisticDto("RPR_STATISTIC", rpr, 0D));

        //Return Rate (RR)
        //TODO: assert not null (retour exist)
        Long retour = (long) session.createQuery("select count(*) from Input i where i.retour = true").getSingleResult();
        Long output = iOutputRepository.count();
        Double rr = (double) retour / (double) output * 100;
        statistics.add(new StatisticDto("RR_STATISTIC", rr, 0D));

        return CompletableFuture.completedFuture(statistics);
    }

    @Override
    public CompletableFuture<List<SeriesListDto>> getPeriodicChart(Map<String, String> params) throws UserFriendlyException {
        Session session = entityManager.unwrap(Session.class);
        var list = new ArrayList<SeriesListDto>();
        List<String> periods = Arrays.asList("MONTH", "DAY", "HOUR");
        String period = params.get("period");
        if (!periods.contains(period))
            throw new UserFriendlyException("period Problem");

        //simple queries
        if (params.get("INPUT_COUNT") != null) {
            List<Object[]> aov = session.createQuery("select " + period + "(i.createdAt) as time, COUNT(*) as value from Input i where i.retour=FALSE group by time").list();
            List<ItemOfSeries> result = new ArrayList<>();
            for (var obj : aov) {
                result.add(new ItemOfSeries(Long.parseLong(obj[0].toString()), Double.parseDouble(obj[1].toString())));
            }
            list.add(new SeriesListDto("INPUT_COUNT", result));
        }

        if (params.get("OUTPUT_COUNT") != null) {
            List<Object[]> aov = session.createQuery("select " + period + "(o.createdAt) as time,COUNT(*) as value from Output o where o.intern=FALSE group by time").list();
            List<ItemOfSeries> result = new ArrayList<>();
            for (var obj : aov) {
                result.add(new ItemOfSeries(Long.parseLong(obj[0].toString()), Double.parseDouble(obj[1].toString())));
            }
            list.add(new SeriesListDto("OUTPUT_COUNT", result));
        }

        if (params.get("INPUT_CHIFFRE") != null) {
            List<Object[]> i = session.createQuery("select " + period + "(i.createdAt) as time, sum(id.qte * p.priceHT) as value " +
                    "from Input i inner join i.inputDetails id inner join id.product p " +
                    "where i.retour=FALSE group by time").list();
            List<ItemOfSeries> result = new ArrayList<>();
            for (var obj : i) {
                result.add(new ItemOfSeries(Long.parseLong(obj[0].toString()), Double.parseDouble(obj[1].toString())));
            }
            list.add(new SeriesListDto("INPUT_CHIFFRE", result));
        }

        if (params.get("OUTPUT_CHIFFRE") != null) {
            List<Object[]> o = session.createQuery("select  " + period + "(o.createdAt) as time, sum(od.qte * od.priceHT) as value " +
                    "from Output o inner join o.outputDetails od group by time").list();
            List<ItemOfSeries> result = new ArrayList<>();
            for (var obj : o) {
                result.add(new ItemOfSeries(Long.parseLong(obj[0].toString()), Double.parseDouble(obj[1].toString())));
            }
            list.add(new SeriesListDto("OUTPUT_CHIFFRE", result));
        }

        if (params.get("AOV_STATISTIC") != null) {
            List<Object[]> aov = session.createQuery("select  " + period + "(i.createdAt) as time, sum((id.qte - COALESCE(od.qte,0)) * p.priceHT) as value " +
                    "from Input i inner join i.inputDetails id inner join id.product p " +
                    "left join p.outputDetails od left join od.output o where  " + period + "(o.createdAt) =  " + period + "(i.createdAt) group by time").list();
            List<ItemOfSeries> result = new ArrayList<>();
            for (var obj : aov) {
                result.add(new ItemOfSeries(Long.parseLong(obj[0].toString()), Double.parseDouble(obj[1].toString())));
            }
            list.add(new SeriesListDto("AOV_STATISTIC", result));
        }

        if (params.get("GP_STATISTIC") != null) {
            List<Object[]> aov = session.createQuery("select " + period + "(i.createdAt) as time,SUM((od.priceHT * od.qte) - (p.priceHT * od.qte)) " +
                    "from Input i inner join i.inputDetails id inner join id.product p inner join p.outputDetails od inner join od.output o " +
                    "where o.intern = FALSE group by time").list();
            List<ItemOfSeries> result = new ArrayList<>();
            for (var obj : aov) {
                result.add(new ItemOfSeries(Long.parseLong(obj[0].toString()), Double.parseDouble(obj[1].toString())));
            }
            list.add(new SeriesListDto("GP_STATISTIC", result));
        }

        if (params.get("STOCK_CHIFFRE") != null) {
            List<Object[]> aov = session.createQuery("select " + period + "(i.createdAt) as time,SUM((id.qte - COALESCE(od.qte,0)) * p.priceHT) " +
                    "from Input i inner join i.inputDetails id inner join id.product p " +
                    "left join p.outputDetails od where i.retour = FALSE group by time").list();
            List<ItemOfSeries> result = new ArrayList<>();
            for (var obj : aov) {
                result.add(new ItemOfSeries(Long.parseLong(obj[0].toString()), Double.parseDouble(obj[1].toString())));
            }
            list.add(new SeriesListDto("STOCK_CHIFFRE", result));
        }


        //complex queries
        if (params.get("RR_STATISTIC") != null) {
            List<Object[]> r = session.createQuery("select  " + period + "(i.createdAt) as time,  count(*) from Input i where i.retour = true group by time").list();
            List<Object[]> o = session.createQuery("select  " + period + "(o.createdAt) as time,  count(*) from Output o where o.intern = false group by time").list();
            List<ItemOfSeries> rr = new ArrayList<>();
            for (int i = 0; i < r.size(); i++) {
                Double tmp = Double.parseDouble(r.get(i)[1].toString()) / Double.parseDouble(o.get(i)[1].toString()) * 100;
                rr.add(new ItemOfSeries(Long.parseLong(r.get(i)[0].toString()), Double.parseDouble(tmp.toString())));
            }
            list.add(new SeriesListDto("RR_STATISTIC", rr));
        }

        if (params.get("RPR_STATISTIC") != null) {
            List<Object[]> o = session.createQuery("select  " + period + "(o.createdAt) as time, count(o.id) as value from Output o where intern=FALSE group by time").list();
            List<Object[]> c = session.createQuery("select  " + period + "(o.createdAt) as time, count(distinct o.actor) as value from Output o where intern=FALSE group by time").list();
            List<ItemOfSeries> rpr = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                Double tmp = Double.parseDouble(o.get(i)[1].toString()) / Double.parseDouble(c.get(i)[1].toString());
                rpr.add(new ItemOfSeries(Long.parseLong(o.get(i)[0].toString()), tmp));
            }
            list.add(new SeriesListDto("RPR_STATISTIC", rpr));
        }
        return CompletableFuture.completedFuture(list);
    }

    //get top 10 profitable products
    public CompletableFuture<List<TreeMapItemDto>> getTreeMapOfTopProducts(int n) {
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

    //get top 10 profitable client
    public CompletableFuture<List<TreeMapItemDto>> getTreeMapOfTopClient(int n) {
        Session session = entityManager.unwrap(Session.class);
        var list = new ArrayList<TreeMapItemDto>();
        List<Object[]> query = session.createSQLQuery("select a.name, SUM(od.qte * od.priceHT - od.qte * p.priceHT) as profit " +
                "from output o " +
                "         inner join outputdetails od on o.id = od.output_id " +
                "         inner join product p on od.product_id = p.id " +
                "         inner join inputdetails id on p.id = id.product_id " +
                "         inner join actor a on o.actor_id = a.id " +
                "where o.intern = FALSE " +
                "and a.tenantId = :tenantId and a.deletedAt is null " +
                "group by o.actor_id " +
                "having profit != 0 " +
                "order by profit desc " +
                "limit :max").setParameter("max", n).setParameter("tenantId", TenantContext.getCurrentTenant()).list();
        for (var row : query) {
            list.add(new TreeMapItemDto(row[0].toString(), ((Number) row[1]).longValue(), null));
        }
        return CompletableFuture.completedFuture(list);
    }
}
