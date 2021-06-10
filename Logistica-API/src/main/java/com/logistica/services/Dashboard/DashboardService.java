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
        statistics.add(new StatisticDto((double) iInputRepository.count(), 0d));
        statistics.add(new StatisticDto((double) iOutputRepository.count(), 0d));
        statistics.add(new StatisticDto((double) iTransferRepository.count(), 0d));
        statistics.add(new StatisticDto((double) iProductRepository.count(), 0d));
        //qte available
        Long tmp = (long) session.createQuery("select sum(id.qte - COALESCE(od.qte,0)) " +
                "from Input i inner join i.inputDetails id inner join id.product p " +
                "left join p.outputDetails od").getSingleResult();
        statistics.add(new StatisticDto(tmp.doubleValue(), 0d));
        //chiffre in stocks
        Double tmp2 = (double) session.createQuery("select sum((id.qte - COALESCE(od.qte,0)) * p.priceHT) " +
                "from Input i inner join i.inputDetails id inner join id.product p " +
                "left join p.outputDetails od").getSingleResult();
        statistics.add(new StatisticDto(tmp2, 0d));
        return CompletableFuture.completedFuture(statistics);
    }

    @Override
    public CompletableFuture<List<SeriesListDto>> getMonthlyTurnover() {
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
    public CompletableFuture<List<SeriesListDto>> getDailyTurnover() {
        var list = new ArrayList<SeriesListDto>();
        Session session = entityManager.unwrap(Session.class);
        // calculate stock money
        list.add(new SeriesListDto(session.createQuery("select DAY(i.createdAt) as time, sum((id.qte - COALESCE(od.qte,0)) * p.priceHT) as value " +
                "from Input i inner join i.inputDetails id inner join id.product p " +
                "left join p.outputDetails od group by time").list()));
        // calculate input money
        list.add(new SeriesListDto(session.createQuery(
                "select DAY(i.createdAt) as time, sum(id.qte * p.priceHT) as value " +
                        "from Input i inner join i.inputDetails id inner join id.product p " +
                        "group by time").list()));
        // calculate output money
        list.add(new SeriesListDto(session.createQuery(
                "select DAY(o.createdAt) as time, sum(od.qte * od.priceHT) as value " +
                        "from Output o inner join o.outputDetails od group by time").list()));
        return CompletableFuture.completedFuture(list);
    }

    @Override
    public CompletableFuture<List<SeriesListDto>> getHourlyTurnover() {
        var list = new ArrayList<SeriesListDto>();
        Session session = entityManager.unwrap(Session.class);
        // calculate stock money
        list.add(new SeriesListDto(session.createQuery("select HOUR(i.createdAt) as time, sum((id.qte - COALESCE(od.qte,0)) * p.priceHT) as value " +
                "from Input i inner join i.inputDetails id inner join id.product p " +
                "left join p.outputDetails od group by time").list()));
        // calculate input money
        list.add(new SeriesListDto(session.createQuery(
                "select HOUR(i.createdAt) as time, sum(id.qte * p.priceHT) as value " +
                        "from Input i inner join i.inputDetails id inner join id.product p " +
                        "group by time").list()));
        // calculate output money
        list.add(new SeriesListDto(session.createQuery(
                "select HOUR(o.createdAt) as time, sum(od.qte * od.priceHT) as value " +
                        "from Output o inner join o.outputDetails od group by time").list()));
        return CompletableFuture.completedFuture(list);
    }

    @Override
    public CompletableFuture<List<SeriesListDto>> getMonthlyQte() {
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
    public CompletableFuture<List<SeriesListDto>> getMonthlyBenefits() {
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
