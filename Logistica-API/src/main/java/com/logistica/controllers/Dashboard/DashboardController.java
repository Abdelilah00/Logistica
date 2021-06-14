package com.logistica.controllers.Dashboard;

import com.logistica.dtos.SeriesListDto;
import com.logistica.dtos.StatisticDto;
import com.logistica.services.Dashboard.IDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    //TODO add period params to all series methods
    @GetMapping(path = "/getMonthlyQte")
    protected List<SeriesListDto> getPeriodicMonthlyQte() throws ExecutionException, InterruptedException {
        return iDashboardService.getPeriodicQte().get();
    }

    @GetMapping(path = "/getMonthlyBenefits")
    protected List<SeriesListDto> getPeriodicBenefits() throws ExecutionException, InterruptedException {
        return iDashboardService.getPeriodicBenefits().get();
    }

    @GetMapping(path = "/getMonthlyTurnover")
    protected List<SeriesListDto> getPeriodicTurnover() throws ExecutionException, InterruptedException {
        return iDashboardService.getPeriodicTurnover().get();
    }
}
