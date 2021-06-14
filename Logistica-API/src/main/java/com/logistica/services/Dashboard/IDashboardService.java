package com.logistica.services.Dashboard;

import com.logistica.dtos.SeriesListDto;
import com.logistica.dtos.StatisticDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IDashboardService {
    CompletableFuture<List<StatisticDto>> getStatistics();

    CompletableFuture<List<SeriesListDto>> getPeriodicTurnover();

    CompletableFuture<List<SeriesListDto>> getPeriodicQte();

    CompletableFuture<List<SeriesListDto>> getPeriodicBenefits();
}
