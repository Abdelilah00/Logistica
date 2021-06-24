package com.logistica.dtos.Dashboard.Analytics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDto {
    private String kpi;
    private Double value;
    private Double growth;
}
