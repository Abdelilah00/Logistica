package com.logistica.controllers.Dashboard;

import com.configuration.Exception.UserFriendlyException;
import com.logistica.dtos.ListOfSeries;
import com.logistica.dtos.StatisticDto;
import com.logistica.dtos.TreeMapItemDto;
import com.logistica.services.Dashboard.IDashboardAnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/dashboardAnalytics")
public class DashboardAnalyticsController {
    @Autowired
    private IDashboardAnalyticsService iDashboardAnalyticsService;

    @GetMapping(path = "/getStatistics")
    protected List<StatisticDto> getStatistics() throws ExecutionException, InterruptedException {
        return iDashboardAnalyticsService.getStatistics().get();
    }

    //TODO: add start end date
    @GetMapping(path = "/getPeriodicChart")
    protected List<ListOfSeries> getPeriodicChart(@RequestParam Map<String, String> params) throws UserFriendlyException, ExecutionException, InterruptedException {
        return iDashboardAnalyticsService.getPeriodicChart(params).get();
    }

    @GetMapping(path = "/getTreeMapOfTop")
    protected List<TreeMapItemDto> getTreeMapOfTopProducts(@RequestParam Map<String, String> params) throws UserFriendlyException, ExecutionException, InterruptedException {
        return iDashboardAnalyticsService.getTreeMapOfTop(params).get();
    }
}
