package com.logistica.services.Dashboard;

import com.configuration.Exception.UserFriendlyException;
import com.logistica.dtos.Dashboard.Alerts.AlertsItem;
import com.logistica.dtos.Dashboard.Predictions.ListOfPredSeries;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IDashboardAlertsService {
    CompletableFuture<List<AlertsItem>> getRealQte(Map<String, String> params) throws UserFriendlyException, IOException, ParseException;

    CompletableFuture<List<AlertsItem>> getPredQte(Map<String, String> params) throws UserFriendlyException, IOException, ParseException;
}
