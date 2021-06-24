package com.logistica.controllers.Dashboard;

import com.configuration.Exception.UserFriendlyException;
import com.logistica.dtos.Dashboard.Alerts.AlertsItem;
import com.logistica.dtos.Dashboard.Predictions.ListOfPredSeries;
import com.logistica.services.Dashboard.IDashboardAlertsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/dashboardAlerts")
public class DashboardAlertsController {
    @Autowired
    private IDashboardAlertsService iDashboardPredictionsService;

    @GetMapping(path = "/getRealQte")
    protected List<AlertsItem> getRealQte(@RequestParam Map<String, String> params) throws UserFriendlyException, ExecutionException, InterruptedException, IOException, ParseException {
        return iDashboardPredictionsService.getRealQte(params).get();
    }

    @GetMapping(path = "/getPredQte")
    protected List<AlertsItem> getPredQte(@RequestParam Map<String, String> params) throws UserFriendlyException, ExecutionException, InterruptedException, IOException, ParseException {
        return iDashboardPredictionsService.getPredQte(params).get();
    }
}
