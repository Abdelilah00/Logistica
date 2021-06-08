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


    @GetMapping(path = "/getMonthlyChiffreAffaire")
    protected List<SeriesListDto> getMonthlyChiffreAffaire() throws ExecutionException, InterruptedException {
        return iDashboardService.getMonthlyChiffreAffaire().get();
    }

    @GetMapping(path = "/getDailyChiffreAffaire")
    protected List<SeriesListDto> getDailyChiffreAffaire() throws ExecutionException, InterruptedException {
        return iDashboardService.getDailyChiffreAffaire().get();
    }

    @GetMapping(path = "/getHourlyChiffreAffaire")
    protected List<SeriesListDto> getHourlyChiffreAffaire() throws ExecutionException, InterruptedException {
        return iDashboardService.getHourlyChiffreAffaire().get();
    }
}
