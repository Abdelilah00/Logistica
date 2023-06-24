package com.logistica.services.Dashboard;

import com.configuration.Exception.UserFriendlyException;
import com.configuration.TenantContext;
import com.logistica.dtos.Dashboard.Analytics.ItemOfSeries;
import com.logistica.dtos.Dashboard.Analytics.ListOfSeries;
import com.logistica.dtos.Dashboard.Analytics.StatisticDto;
import com.logistica.dtos.Dashboard.Analytics.TreeMapItemDto;
import com.logistica.repositories.Commands.IActorRepository;
import com.logistica.repositories.Products.IInputRepository;
import com.logistica.repositories.Products.IOutputRepository;
import com.logistica.repositories.Products.IProductRepository;
import com.logistica.repositories.Products.ITransferRepository;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class DashboardAnalyticsService implements IDashboardAnalyticsService {

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
    @Autowired
    private IActorRepository iActorRepository;

    private static final Logger logger = LoggerFactory.getLogger(DashboardAnalyticsService.class.getName());
    Session session;

    @Override
    //todo : params for the hidden stat
    public CompletableFuture<List<StatisticDto>> getStatistics() throws ExecutionException, InterruptedException {
        var statistics = new ArrayList<StatisticDto>();
        session = entityManager.unwrap(Session.class);

        CompletableFuture<StatisticDto>[] completableFutures = new CompletableFuture[]{INPUT_CHIFFRE(), OUTPUT_CHIFFRE(),
                RR_STATISTIC(), STOCK_CHIFFRE(), AOV_STATISTIC(), GP_STATISTIC(), RPR_STATISTIC(),
                INPUT_COUNT(), OUTPUT_COUNT(), TRANSFER_COUNT(), PRODUCT_COUNT(), CLIENT_COUNT()};

        CompletableFuture.allOf(completableFutures).thenAccept(it -> {
            for (int i = 0; i < completableFutures.length; i++) {
                StatisticDto x = completableFutures[i].join();
                logger.info("completed => " + x.toString());
                statistics.add(x);
            }
        }).get();

        return CompletableFuture.completedFuture(statistics);
    }

    @Override
    public CompletableFuture<List<ListOfSeries>> getPeriodicChart(Map<String, String> params) throws UserFriendlyException {
        Session session = entityManager.unwrap(Session.class);
        var list = new ArrayList<ListOfSeries>();
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
            list.add(new ListOfSeries("INPUT_COUNT", result));
        }

        if (params.get("OUTPUT_COUNT") != null) {
            List<Object[]> aov = session.createQuery("select " + period + "(o.createdAt) as time,COUNT(*) as value from Output o where o.intern=FALSE group by time").list();
            List<ItemOfSeries> result = new ArrayList<>();
            for (var obj : aov) {
                result.add(new ItemOfSeries(Long.parseLong(obj[0].toString()), Double.parseDouble(obj[1].toString())));
            }
            list.add(new ListOfSeries("OUTPUT_COUNT", result));
        }

        if (params.get("INPUT_CHIFFRE") != null) {
            List<Object[]> i = session.createQuery("select " + period + "(i.createdAt) as time, sum(id.qte * p.priceHT) as value " +
                    "from Input i inner join i.inputDetails id inner join id.product p " +
                    "where i.retour=FALSE group by time").list();
            List<ItemOfSeries> result = new ArrayList<>();
            for (var obj : i) {
                try {
                    result.add(new ItemOfSeries(Long.parseLong(obj[0].toString()), Double.parseDouble(obj[1].toString())));
                } catch (Exception exception) {

                }
            }
            list.add(new ListOfSeries("INPUT_CHIFFRE", result));
        }

        if (params.get("OUTPUT_CHIFFRE") != null) {
            List<Object[]> o = session.createQuery("select  " + period + "(o.createdAt) as time, sum(od.qte * od.priceHT) as value " +
                    "from Output o inner join o.outputDetails od group by time").list();
            List<ItemOfSeries> result = new ArrayList<>();
            for (var obj : o) {
                try {
                    result.add(new ItemOfSeries(Long.parseLong(obj[0].toString()), Double.parseDouble(obj[1].toString())));
                }catch (Exception e){

                }
            }
            list.add(new ListOfSeries("OUTPUT_CHIFFRE", result));
        }

        if (params.get("AOV_STATISTIC") != null) {
            List<Object[]> aov = session.createQuery("select  " + period + "(i.createdAt) as time, sum((id.qte - COALESCE(od.qte,0)) * p.priceHT) as value " +
                    "from Input i inner join i.inputDetails id inner join id.product p " +
                    "left join p.outputDetails od left join od.output o where  " + period + "(o.createdAt) =  " + period + "(i.createdAt) group by time").list();
            List<ItemOfSeries> result = new ArrayList<>();
            for (var obj : aov) {
                result.add(new ItemOfSeries(Long.parseLong(obj[0].toString()), Double.parseDouble(obj[1].toString())));
            }
            list.add(new ListOfSeries("AOV_STATISTIC", result));
        }

        if (params.get("GP_STATISTIC") != null) {
            List<Object[]> aov = session.createQuery("select " + period + "(i.createdAt) as time,SUM((od.priceHT * od.qte) - (p.priceHT * od.qte)) " +
                    "from Input i inner join i.inputDetails id inner join id.product p inner join p.outputDetails od inner join od.output o " +
                    "where o.intern = FALSE group by time").list();
            List<ItemOfSeries> result = new ArrayList<>();
            for (var obj : aov) {
                result.add(new ItemOfSeries(Long.parseLong(obj[0].toString()), Double.parseDouble(obj[1].toString())));
            }
            list.add(new ListOfSeries("GP_STATISTIC", result));
        }

        if (params.get("STOCK_CHIFFRE") != null) {
            List<Object[]> aov = session.createQuery("select " + period + "(i.createdAt) as time,SUM((id.qte - COALESCE(od.qte,0)) * p.priceHT) " +
                    "from Input i inner join i.inputDetails id inner join id.product p " +
                    "left join p.outputDetails od where i.retour = FALSE group by time").list();
            List<ItemOfSeries> result = new ArrayList<>();
            for (var obj : aov) {
                result.add(new ItemOfSeries(Long.parseLong(obj[0].toString()), Double.parseDouble(obj[1].toString())));
            }
            list.add(new ListOfSeries("STOCK_CHIFFRE", result));
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
            list.add(new ListOfSeries("RR_STATISTIC", rr));
        }

        if (params.get("RPR_STATISTIC") != null) {
            List<Object[]> o = session.createQuery("select  " + period + "(o.createdAt) as time, count(o.id) as value from Output o where intern=FALSE group by time").list();
            List<Object[]> c = session.createQuery("select  " + period + "(o.createdAt) as time, count(distinct o.actor) as value from Output o where intern=FALSE group by time").list();
            List<ItemOfSeries> rpr = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                Double tmp = Double.parseDouble(o.get(i)[1].toString()) / Double.parseDouble(c.get(i)[1].toString());
                rpr.add(new ItemOfSeries(Long.parseLong(o.get(i)[0].toString()), tmp));
            }
            list.add(new ListOfSeries("RPR_STATISTIC", rpr));
        }
        return CompletableFuture.completedFuture(list);
    }

    //get top 10 profitable products
    public CompletableFuture<List<TreeMapItemDto>> getTreeMapOfTop(Map<String, String> params) throws UserFriendlyException {
        var filters = Arrays.asList("products", "clients", "suppliers", "services");
        int n = Integer.parseInt(params.get("n"));
        var filter = params.get("filter");
        if (!filters.contains(filter)) throw new UserFriendlyException("error in your filter");

        Session session = entityManager.unwrap(Session.class);
        var list = new ArrayList<TreeMapItemDto>();
        List<Object[]> query = new ArrayList<>();

        switch (filter) {
            case "products":
                query = session.createSQLQuery("select p.name, SUM(od.qte * od.priceHT - od.qte * p.priceHT) as profit " +
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
                break;
            case "clients":
                query = session.createSQLQuery("select a.name, SUM(od.qte * od.priceHT - od.qte * p.priceHT) as profit " +
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
                break;
            case "suppliers":
                query = session.createSQLQuery("select a.name, SUM(od.qte * od.priceHT - od.qte * p.priceHT) as profit " +
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
            case "services":
                query = session.createSQLQuery("select a.name, SUM(od.qte * od.priceHT - od.qte * p.priceHT) as profit " +
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
                break;
        }

        for (var row : query) {
            list.add(new TreeMapItemDto(row[0].toString(), ((Number) row[1]).longValue(), null));
        }
        return CompletableFuture.completedFuture(list);
    }

    //region statistic
    private CompletableFuture<StatisticDto> INPUT_CHIFFRE() {
        return CompletableFuture.supplyAsync(() -> {
            var tmp = new StatisticDto("INPUT_CHIFFRE", (double) session.createQuery(
                    "select sum(id.qte * p.priceHT) as value " +
                            "from Input i inner join i.inputDetails id inner join id.product p where i.retour=FALSE").getSingleResult(), 0D);
            return tmp;
        });
    }

    private CompletableFuture<StatisticDto> OUTPUT_CHIFFRE() {
        return CompletableFuture.supplyAsync(() -> {
            var tmp = new StatisticDto("OUTPUT_CHIFFRE", (double) session.createQuery(
                    "select sum(od.qte * od.priceHT) as value " +
                            "from Output o inner join o.outputDetails od where o.intern=FALSE").getSingleResult(), 0D);
            return tmp;
        });
    }

    //Return Rate (RR)
    private CompletableFuture<StatisticDto> RR_STATISTIC() {
        return CompletableFuture.supplyAsync(() -> {
            //TODO: assert not null (retour exist)
            Long retour = (long) session.createQuery("select count(*) from Input i where i.retour = true").getSingleResult();
            Long output = iOutputRepository.count();
            Double rr = (double) retour / (double) output * 100;
            return new StatisticDto("RR_STATISTIC", rr, 0D);
        });
    }

    //chiffre in stocks
    private CompletableFuture<StatisticDto> STOCK_CHIFFRE() {
        return CompletableFuture.supplyAsync(() -> {
            Double chiffreStock = CompletableFuture.completedFuture((double) session.createQuery("select sum((id.qte - COALESCE(od.qte,0)) * p.priceHT) " +
                    "from Input i inner join i.inputDetails id inner join id.product p " +
                    "left join p.outputDetails od where i.retour = FALSE").getSingleResult()).join();
            return new StatisticDto("STOCK_CHIFFRE", chiffreStock, 0D);
        });
    }

    //Average Order Value (AOV) = total revenue / number of order
    private CompletableFuture<StatisticDto> AOV_STATISTIC() {
        return CompletableFuture.supplyAsync(() -> {
            List<Double> tmp = session.createQuery("select SUM((od.priceHT * od.qte) - (p.priceHT * od.qte)) as s" +
                    "  from Input i  inner join i.inputDetails id inner join id.product p inner join p.outputDetails od inner join od.output o" +
                    "  where o.intern = FALSE" +
                    "  group by o.id").getResultList();
            Double aov = tmp.stream().mapToDouble(d -> d).average().orElse(0.0);
            return (new StatisticDto("AOV_STATISTIC", aov, 0D));
        });
    }

    //Gross Profit (GP)= Total Cost of Goods Sold – Total Number of sales
    private CompletableFuture<StatisticDto> GP_STATISTIC() {
        return CompletableFuture.supplyAsync(() -> {
            Double gp = CompletableFuture.completedFuture(Double.valueOf(session.createQuery("select SUM((od.priceHT * od.qte) - (p.priceHT * od.qte))" +
                    "  from Input i inner join i.inputDetails id inner join id.product p inner join p.outputDetails od inner join od.output o" +
                    " where o.intern = FALSE").getSingleResult().toString())).join();
            return (new StatisticDto("GP_STATISTIC", gp, 0D));
        });
    }

    //Repeat Purchase Rate (RPR) = Purchases from Repeat Customers / Total Purchase
    private CompletableFuture<StatisticDto> RPR_STATISTIC() {
        return CompletableFuture.supplyAsync(() -> {
            Long o = (long) session.createQuery("select count(o.id) from Output o where intern=FALSE").getSingleResult();
            Long c = (long) session.createQuery("select count(distinct o.actor) from Output o where intern=FALSE").getSingleResult();
            Double rpr = (double) o / (double) c;
            return (new StatisticDto("RPR_STATISTIC", rpr, 0D));
        });
    }

    private CompletableFuture<StatisticDto> INPUT_COUNT() {
        return CompletableFuture.supplyAsync(() -> new StatisticDto("INPUT_COUNT_COGS", (double) iInputRepository.count(), 0D));
    }

    private CompletableFuture<StatisticDto> OUTPUT_COUNT() {
        return CompletableFuture.supplyAsync(() -> new StatisticDto("OUTPUT_COUNT_GP", (double) iOutputRepository.count(), 0D));
    }

    private CompletableFuture<StatisticDto> TRANSFER_COUNT() {
        return CompletableFuture.supplyAsync(() -> new StatisticDto("TRANSFER_COUNT", (double) iTransferRepository.count(), 0D));
    }

    private CompletableFuture<StatisticDto> PRODUCT_COUNT() {
        return CompletableFuture.supplyAsync(() -> new StatisticDto("PRODUCT_COUNT", (double) iProductRepository.count(), 0D));
    }

    private CompletableFuture<StatisticDto> CLIENT_COUNT() {
        return CompletableFuture.supplyAsync(() -> new StatisticDto("CLIENT_COUNT", (double) iActorRepository.count(), 0D));
    }
    //endregion
}
