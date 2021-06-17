package com.logistica.services.Dashboard;

import com.configuration.Exception.UserFriendlyException;
import com.logistica.dtos.ItemOfSeries;
import com.logistica.dtos.SeriesListDto;
import com.logistica.dtos.StatisticDto;
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
    //todo params for the hidden stat
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

        if (params.get("AOV_STATISTIC") != null)
            list.add(new SeriesListDto("AOV_STATISTIC", session.createQuery("select  " + period + "(i.createdAt) as time, sum((id.qte - COALESCE(od.qte,0)) * p.priceHT) as value " +
                    "from Input i inner join i.inputDetails id inner join id.product p " +
                    "left join p.outputDetails od left join od.output o where  " + period + "(o.createdAt) =  " + period + "(i.createdAt) group by time").list()));

        if (params.get("INPUT_CHIFFRE") != null)
            list.add(new SeriesListDto("INPUT_CHIFFRE", session.createQuery(
                    "select " + period + "(i.createdAt) as time, sum(id.qte * p.priceHT) as value " +
                            "from Input i inner join i.inputDetails id inner join id.product p " +
                            "group by time").list()));

        if (params.get("OUTPUT_CHIFFRE") != null)
            list.add(new SeriesListDto("OUTPUT_CHIFFRE", session.createQuery(
                    "select  " + period + "(o.createdAt) as time, sum(od.qte * od.priceHT) as value " +
                            "from Output o inner join o.outputDetails od group by time").list()));

        if (params.get("RPR_STATISTIC") != null) {
            List<ItemOfSeries> o = session.createQuery("select  " + period + "(o.createdAt) as time,count(o.id) from Output o where intern=FALSE group by time").getResultList();
            List<ItemOfSeries> c = session.createQuery("select  " + period + "(o.createdAt) as time,count(distinct o.actor) from Output o where intern=FALSE group by time").getResultList();
            List<ItemOfSeries> rpr = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                rpr.add(new ItemOfSeries(o.get(i).getTime(), o.get(i).getValue() / c.get(i).getValue()));
            }
            list.add(new SeriesListDto("RPR_STATISTIC", rpr));
        }

        return CompletableFuture.completedFuture(list);
    }

}
