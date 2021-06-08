package com.logistica.services.Dashboard;

import com.logistica.dtos.SeriesDto;
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
    public CompletableFuture<List<SeriesDto>> getMonthlyChiffreAffaire() {
        //todo sum of p.price*ps.qte
        Session session = entityManager.unwrap(Session.class);
        List<SeriesDto> list = session.createQuery(
                "select month(i.createdAt) as time, sum(sp.qte * p.priceHT) as value from Input i join StockProduct sp join Product p group by time"
        ).list();
        return CompletableFuture.completedFuture(list);
    }
}
