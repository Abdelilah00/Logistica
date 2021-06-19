package com.logistica.controllers.Dashboard;

import com.configuration.Exception.UserFriendlyException;
import com.logistica.dtos.*;
import com.logistica.services.Dashboard.IDashboardAnalyticsService;
import com.logistica.services.Dashboard.IDashboardPredictionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/dashboardPredictions")
public class DashboardPredictionsController {
    @Autowired
    private IDashboardPredictionsService iDashboardPredictionsService;

    @GetMapping(path = "/getChart")
    protected List<ItemOfPredSeries> getChart(@RequestParam Map<String, String> params) throws UserFriendlyException, ExecutionException, InterruptedException {
        return iDashboardPredictionsService.getChart(params).get();
    }
}
