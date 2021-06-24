package com.logistica.dtos.Dashboard.Alerts;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlertsItem {
    private String name;
    private Double max;
    private Double acc;
    private Double min;
}
