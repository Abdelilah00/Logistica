package com.logistica.services.Dashboard;

import com.logistica.dtos.RevenueDto;
import org.hibernate.annotations.Formula;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IDashboardService {
    CompletableFuture<Long> getTotaleInput();

    CompletableFuture<Long> getTotaleOutput();

    CompletableFuture<Long> getTotaleTransfer();

    CompletableFuture<Long> getTotaleAvailableStock();

    CompletableFuture<Long> getAvgAvailableStock();

    CompletableFuture<Long> getTotaleSales();

    CompletableFuture<List<RevenueDto>> getMonthlyChiffreAffaire();
}
