package com.logistica.services.Dashboard;

import com.configuration.Exception.UserFriendlyException;
import com.logistica.dtos.Dashboard.Predictions.ListOfPredSeries;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface IDashboardPredictionsService {
    CompletableFuture<ListOfPredSeries> getChart(Map<String, String> params) throws UserFriendlyException, IOException, ParseException;
}
