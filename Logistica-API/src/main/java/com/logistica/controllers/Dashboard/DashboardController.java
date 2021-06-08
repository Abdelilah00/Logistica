package com.logistica.controllers.Dashboard;

import com.logistica.dtos.SeriesDto;
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

    @GetMapping(path = "/getTotaleInput")
    protected Long getTotaleInput() throws ExecutionException, InterruptedException {
        return iDashboardService.getTotaleInput().get();
    }

    @GetMapping(path = "/getTotaleOutput")
    protected Long getTotaleOutput() throws ExecutionException, InterruptedException {
        return iDashboardService.getTotaleOutput().get();
    }

    @GetMapping(path = "/getTotaleTransfer")
    protected Long getTotaleTransfer() throws ExecutionException, InterruptedException {
        return iDashboardService.getTotaleTransfer().get();
    }

    //todo chiffre d'affaire mensuelle
    @GetMapping(path = "/getMonthlyChiffreAffaire")
    protected List<SeriesDto> getMonthlyChiffreAffaire() throws ExecutionException, InterruptedException {
        return iDashboardService.getMonthlyChiffreAffaire().get();
    }
}
