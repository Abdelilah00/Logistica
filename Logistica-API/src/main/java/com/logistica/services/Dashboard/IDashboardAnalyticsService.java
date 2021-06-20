package com.logistica.services.Dashboard;

import com.configuration.Exception.UserFriendlyException;
import com.logistica.dtos.ListOfSeries;
import com.logistica.dtos.StatisticDto;
import com.logistica.dtos.TreeMapItemDto;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface IDashboardAnalyticsService {
    CompletableFuture<List<StatisticDto>> getStatistics();

    CompletableFuture<List<ListOfSeries>> getPeriodicChart(Map<String, String> params) throws UserFriendlyException;

    CompletableFuture<List<TreeMapItemDto>> getTreeMapOfTop( Map<String, String> params) throws UserFriendlyException;
}
