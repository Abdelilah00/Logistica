package com.logistica.controllers.Dashboard;

import com.configuration.Exception.UserFriendlyException;
import com.logistica.dtos.SeriesListDto;
import com.logistica.dtos.StatisticDto;
import com.logistica.dtos.TreeMapItemDto;
import com.logistica.services.Dashboard.IDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/dashboard")
public class DashboardController {
    @Autowired
    private IDashboardService iDashboardService;

    @GetMapping(path = "/getStatistics")
    protected List<StatisticDto> getStatistics() throws ExecutionException, InterruptedException {
        return iDashboardService.getStatistics().get();
    }

    //TODO: add start end date
    @GetMapping(path = "/getPeriodicChart")
    protected List<SeriesListDto> getPeriodicChart(@RequestParam Map<String, String> params) throws ExecutionException, InterruptedException, UserFriendlyException {
        return iDashboardService.getPeriodicChart(params).get();
    }

    @GetMapping(path = "/getTreeMapOfTopProducts")
    protected List<TreeMapItemDto> getTreeMapOfTopProducts(@RequestParam Map<String, String> params) throws ExecutionException, InterruptedException {
        return iDashboardService.getTreeMapOfTopProducts(10).get();
    }

    @GetMapping(path = "/getTreeMapOfTopClient")
    protected List<TreeMapItemDto> getTreeMapOfTopClient(@RequestParam Map<String, String> params) throws ExecutionException, InterruptedException {
        return iDashboardService.getTreeMapOfTopClient(10).get();
    }
}
