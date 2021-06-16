package com.logistica.controllers.Dashboard;

import com.logistica.dtos.SeriesListDto;
import com.logistica.dtos.StatisticDto;
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

    @GetMapping(path = "/getChart")
    protected List<SeriesListDto> getChart(@RequestParam Map<String, String> params) throws ExecutionException, InterruptedException {
        if (params.get("period").equals("hour"))
            return iDashboardService.getMonthly(params).get();
        else if (params.get("period").equals("day"))
            return iDashboardService.getMonthly(params).get();
        else
            return iDashboardService.getMonthly(params).get();
    }
}
