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
    protected List<SeriesListDto> getMonthlyQte() throws ExecutionException, InterruptedException {
        return iDashboardService.getMonthlyQte().get();
    }

    @GetMapping(path = "/getMonthlyBenefits")
    protected List<SeriesListDto> getMonthlyBenefits() throws ExecutionException, InterruptedException {
        return iDashboardService.getMonthlyBenefits().get();
    }

    //region merge to one method with period params &
    @GetMapping(path = "/getMonthlyTurnover")
    protected List<SeriesListDto> getMonthlyTurnover() throws ExecutionException, InterruptedException {
        return iDashboardService.getMonthlyTurnover().get();
    }

    @GetMapping(path = "/getDailyTurnover")
    protected List<SeriesListDto> getDailyTurnover() throws ExecutionException, InterruptedException {
        return iDashboardService.getDailyTurnover().get();
    }

    @GetMapping(path = "/getHourlyTurnover")
    protected List<SeriesListDto> getHourlyTurnover() throws ExecutionException, InterruptedException {
        return iDashboardService.getHourlyTurnover().get();
    }
    //endregion
}
