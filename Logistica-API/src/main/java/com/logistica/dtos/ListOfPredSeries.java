package com.logistica.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListOfPredSeries {
    private Long max;
    private Long med;
    private Long min;
    private List<ItemOfPredSeries> items = new ArrayList<>();
}
