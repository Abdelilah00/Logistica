package com.logistica.services.Dashboard;

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
import java.util.List;
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
    public CompletableFuture<List<StatisticDto>> getStatistics() {
        Session session = entityManager.unwrap(Session.class);

        var statistics = new ArrayList<StatisticDto>();
        statistics.add(new StatisticDto("INPUT_STATISTIC", (double) iInputRepository.count(), 0d));
        statistics.add(new StatisticDto("OUTPUT_STATISTIC", (double) iOutputRepository.count(), 0d));
        statistics.add(new StatisticDto("TRANSFER_STATISTIC", (double) iTransferRepository.count(), 0d));
        statistics.add(new StatisticDto("PRODUCT_STATISTIC", (double) iProductRepository.count(), 0d));

        //qte available
        Long qteAvailable = (long) session.createQuery("select sum(id.qte - COALESCE(od.qte,0)) " +
                "from Input i inner join i.inputDetails id inner join id.product p " +
                "left join p.outputDetails od").getSingleResult();
        statistics.add(new StatisticDto("QTE_A_STATISTIC", qteAvailable.doubleValue(), 0d));

        //chiffre in stocks
        Double chiffreStock = (double) session.createQuery("select sum((id.qte - COALESCE(od.qte,0)) * p.priceHT) " +
                "from Input i inner join i.inputDetails id inner join id.product p " +
                "left join p.outputDetails od").getSingleResult();
        statistics.add(new StatisticDto("C_S_STATISTIC", chiffreStock, 0d));

        //Average Order Value (AOV) = total revenue / number of order
        List<Double> tmp = session.createQuery("select SUM((od.priceHT * od.qte) - (p.priceHT * od.qte)) as s" +
                "  from Input i  inner join i.inputDetails id inner join id.product p inner join p.outputDetails od inner join od.output o" +
                "  where o.intern = FALSE" +
                "  group by o.id").getResultList();
        var aov = tmp.stream().mapToDouble(d -> d).average().orElse(0.0);
        statistics.add(new StatisticDto("AOV_STATISTIC", aov, 0d));

        //Gross Profit (GP)= Total Cost of Goods Sold – Total Number of sales
        Double gp = (double) session.createQuery("select SUM((od.priceHT * od.qte) - (p.priceHT * od.qte))" +
                "  from Input i inner join i.inputDetails id inner join id.product p inner join p.outputDetails od inner join od.output o" +
                " where o.intern = FALSE").getSingleResult();
        statistics.add(new StatisticDto("GP_STATISTIC", gp, 0d));

        //Repeat Purchase Rate (RPR) = Purchases from Repeat Customers / Total Purchase
        Double rpr = (double) session.createQuery("select SUM((od.priceHT * od.qte) - (p.priceHT * od.qte))" +
                "  from Input i inner join i.inputDetails id inner join id.product p inner join p.outputDetails od inner join od.output o" +
                "  where o.intern = FALSE").getSingleResult();
        statistics.add(new StatisticDto("RPR_STATISTIC", rpr, 0d));

        //Return Rate (RR)
        //TODO: assert not null (retour exist)
        Double rr = (double) session.createQuery("select count(i)/count(o)" +
                "from Input i inner join i.inputDetails id inner join id.product p inner join p.outputDetails od inner join od.output o " +
                "where i.retour = FALSE ").getSingleResult();
        statistics.add(new StatisticDto("RR_STATISTIC", rr, 0d));

        return CompletableFuture.completedFuture(statistics);
    }

    @Override
    public CompletableFuture<List<SeriesListDto>> getPeriodicTurnover() {
        var list = new ArrayList<SeriesListDto>();
        Session session = entityManager.unwrap(Session.class);
        // calculate stock money
        list.add(new SeriesListDto(session.createQuery("select MONTH(i.createdAt) as time, sum((id.qte - COALESCE(od.qte,0)) * p.priceHT) as value " +
                "from Input i inner join i.inputDetails id inner join id.product p " +
                "left join p.outputDetails od left join od.output o where MONTH(o.createdAt) = MONTH(i.createdAt) group by time").list()));
        // calculate input money
        list.add(new SeriesListDto(session.createQuery(
                "select MONTH(i.createdAt) as time, sum(id.qte * p.priceHT) as value " +
                        "from Input i inner join i.inputDetails id inner join id.product p " +
                        "group by time").list()));
        // calculate output money
        list.add(new SeriesListDto(session.createQuery(
                "select MONTH(o.createdAt) as time, sum(od.qte * od.priceHT) as value " +
                        "from Output o inner join o.outputDetails od group by time").list()));
        return CompletableFuture.completedFuture(list);
    }

    @Override
    public CompletableFuture<List<SeriesListDto>> getPeriodicQte() {
        var list = new ArrayList<SeriesListDto>();
        Session session = entityManager.unwrap(Session.class);
        // calculate stock money
        list.add(new SeriesListDto(session.createQuery("select MONTH(i.createdAt) as time, sum(id.qte - COALESCE(od.qte,0)) as value " +
                "from Input i inner join i.inputDetails id inner join id.product p " +
                "left join p.outputDetails od left join od.output o where MONTH(o.createdAt) = MONTH(i.createdAt) group by time").list()));
        // calculate input money
        list.add(new SeriesListDto(session.createQuery(
                "select MONTH(i.createdAt) as time, sum(id.qte) as value " +
                        "from Input i inner join i.inputDetails id inner join id.product p " +
                        "group by time").list()));
        // calculate output money
        list.add(new SeriesListDto(session.createQuery(
                "select MONTH(o.createdAt) as time, sum(od.qte) as value " +
                        "from Output o inner join o.outputDetails od group by time").list()));
        return CompletableFuture.completedFuture(list);
    }

    @Override
    public CompletableFuture<List<SeriesListDto>> getPeriodicBenefits() {
        var list = new ArrayList<SeriesListDto>();
        Session session = entityManager.unwrap(Session.class);
        // calculate stock money
        list.add(new SeriesListDto(session.createQuery("select MONTH(i.createdAt) as time, sum((id.qte*p.priceHT) - (COALESCE(od.qte,0)*od.priceHT)) as value " +
                "from Input i inner join i.inputDetails id inner join id.product p " +
                "left join p.outputDetails od left join od.output o where MONTH(o.createdAt) = MONTH(i.createdAt) group by time").list()));
        // calculate input money
        list.add(new SeriesListDto(session.createQuery(
                "select MONTH(i.createdAt) as time, sum(id.qte) as value " +
                        "from Input i inner join i.inputDetails id inner join id.product p " +
                        "group by time").list()));
        // calculate output money
        list.add(new SeriesListDto(session.createQuery(
                "select MONTH(o.createdAt) as time, sum(od.qte) as value " +
                        "from Output o inner join o.outputDetails od group by time").list()));
        return CompletableFuture.completedFuture(list);
    }

}
