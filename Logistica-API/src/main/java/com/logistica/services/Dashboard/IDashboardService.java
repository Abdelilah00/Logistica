package com.logistica.services.Dashboard;

import com.configuration.Exception.UserFriendlyException;
import com.logistica.dtos.SeriesListDto;
import com.logistica.dtos.StatisticDto;
import com.logistica.dtos.TreeMapItemDto;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface IDashboardService {
    CompletableFuture<List<StatisticDto>> getStatistics();

    CompletableFuture<List<SeriesListDto>> getPeriodicChart(Map<String, String> params) throws UserFriendlyException;

    CompletableFuture<List<TreeMapItemDto>> getTreeMapOfTopProducts(int n);

    CompletableFuture<List<TreeMapItemDto>> getTreeMapOfTopClient(int n);
}
