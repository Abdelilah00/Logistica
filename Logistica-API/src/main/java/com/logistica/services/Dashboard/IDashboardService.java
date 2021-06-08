package com.logistica.services.Dashboard;

import com.logistica.dtos.SeriesListDto;
import com.logistica.dtos.StatisticDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IDashboardService {
    CompletableFuture<List<StatisticDto>> getStatistics();


    CompletableFuture<List<SeriesListDto>> getMonthlyChiffreAffaire();

    CompletableFuture<List<SeriesListDto>> getDailyChiffreAffaire();

    CompletableFuture<List<SeriesListDto>> getHourlyChiffreAffaire();
}
