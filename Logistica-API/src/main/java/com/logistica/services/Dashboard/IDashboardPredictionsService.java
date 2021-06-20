package com.logistica.services.Dashboard;

import com.configuration.Exception.UserFriendlyException;
import com.logistica.dtos.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface IDashboardPredictionsService {
    CompletableFuture<ListOfPredSeries> getChart(Map<String, String> params) throws UserFriendlyException;
}
