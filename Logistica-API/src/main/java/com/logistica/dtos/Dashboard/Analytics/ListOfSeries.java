package com.logistica.dtos.Dashboard.Analytics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ListOfSeries {
    private String kpi;
    private List<ItemOfSeries> items;
}
