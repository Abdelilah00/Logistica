package com.logistica.services.Dashboard;

import com.logistica.dtos.SeriesListDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IDashboardService {
    CompletableFuture<Long> getTotaleInput();

    CompletableFuture<Long> getTotaleOutput();

    CompletableFuture<Long> getTotaleTransfer();

    CompletableFuture<Long> getTotaleAvailableStock();

    CompletableFuture<Long> getAvgAvailableStock();

    CompletableFuture<Long> getTotaleSales();

    CompletableFuture<List<SeriesListDto>> getMonthlyChiffreAffaire();
}
