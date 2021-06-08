package com.logistica.services.Dashboard;

import com.logistica.dtos.SeriesListDto;
import com.logistica.repositories.Products.IInputRepository;
import com.logistica.repositories.Products.IOutputRepository;
import com.logistica.repositories.Products.ITransferRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.List;

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

    @Override
    public CompletableFuture<Long> getTotaleInput() {
        return CompletableFuture.completedFuture(iInputRepository.count());
    }

    @Override
    public CompletableFuture<Long> getTotaleOutput() {
        return CompletableFuture.completedFuture(iOutputRepository.count());
    }

    @Override
    public CompletableFuture<Long> getTotaleTransfer() {
        return CompletableFuture.completedFuture(iTransferRepository.count());
    }

    @Override
    public CompletableFuture<Long> getTotaleAvailableStock() {
        return null;
    }

    @Override
    public CompletableFuture<Long> getAvgAvailableStock() {
        return null;
    }

    @Override
    public CompletableFuture<Long> getTotaleSales() {
        return null;
    }

    @Override
    public CompletableFuture<List<SeriesListDto>> getMonthlyChiffreAffaire() {
        var list = new ArrayList<SeriesListDto>();
        Session session = entityManager.unwrap(Session.class);
        //calculate stock money
        list.add(new SeriesListDto(session.createQuery(
                "select MONTH(i.createdAt) as time, sum((id.qte - COALESCE(od.qte,0)) * p.priceHT) as value " +
                        "from Input i inner join i.inputDetails id inner join id.product p " +
                        "left join p.outputDetails od group by time").list()));
        //todo calculate input money
        list.add(new SeriesListDto(session.createQuery(
                "select MONTH(i.createdAt) as time, sum((id.qte - COALESCE(od.qte,0)) * p.priceHT) as value " +
                        "from Input i inner join i.inputDetails id inner join id.product p " +
                        "left join p.outputDetails od group by time").list()));
        //todo calculate output money
        list.add(new SeriesListDto(session.createQuery(
                "select MONTH(i.createdAt) as time, sum((id.qte - COALESCE(od.qte,0)) * p.priceHT) as value " +
                        "from Input i inner join i.inputDetails id inner join id.product p " +
                        "left join p.outputDetails od group by time").list()));
        return CompletableFuture.completedFuture(list);
    }
}
