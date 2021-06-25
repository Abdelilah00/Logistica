package com.logistica.dtos.Dashboard.Alerts;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlertsItem {
    private String name;
    private Long max;
    private Long acc = 0L;
    private Long min;
}
