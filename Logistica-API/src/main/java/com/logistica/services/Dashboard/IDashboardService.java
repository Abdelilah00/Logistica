package com.logistica.services.Dashboard;

import com.logistica.dtos.SeriesListDto;
import com.logistica.dtos.StatisticDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IDashboardService {
    CompletableFuture<List<StatisticDto>> getStatistics();


    CompletableFuture<List<SeriesListDto>> getMonthlyTurnover();

    CompletableFuture<List<SeriesListDto>> getDailyTurnover();

    CompletableFuture<List<SeriesListDto>> getHourlyTurnover();

    CompletableFuture<List<SeriesListDto>> getMonthlyQte();
    CompletableFuture<List<SeriesListDto>> getMonthlyBenefits();

}
