package com.logistica.services.Dashboard;

import com.configuration.Exception.UserFriendlyException;
import com.logistica.dtos.Dashboard.Analytics.ListOfSeries;
import com.logistica.dtos.Dashboard.Analytics.StatisticDto;
import com.logistica.dtos.Dashboard.Analytics.TreeMapItemDto;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface IDashboardAnalyticsService {
    CompletableFuture<List<StatisticDto>> getStatistics();

    CompletableFuture<List<ListOfSeries>> getPeriodicChart(Map<String, String> params) throws UserFriendlyException;

    CompletableFuture<List<TreeMapItemDto>> getTreeMapOfTop( Map<String, String> params) throws UserFriendlyException;
}
