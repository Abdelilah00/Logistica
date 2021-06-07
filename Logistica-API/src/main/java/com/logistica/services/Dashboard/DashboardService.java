package com.logistica.services.Dashboard;

import com.logistica.dtos.RevenueDto;
import com.logistica.repositories.Products.IInputRepository;
import com.logistica.repositories.Products.IOutputRepository;
import com.logistica.repositories.Products.ITransferRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public CompletableFuture<List<RevenueDto>> getMonthlyChiffreAffaire() {
        //todo fix this session problem maybe
        Session session = entityManager.unwrap(Session.class);
        List<RevenueDto> list = session.createQuery(
                "select month(i.createdAt) as time, sum(td.qte * td.priceHT) as revenue from Input i join i.transactionDetails td group by time"
        ).list();
        return CompletableFuture.completedFuture(list);
    }
}
