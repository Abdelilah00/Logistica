package com.logistica.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class TreeMapItemDto {
    private String name;
    private long value;
    private List<TreeMapItemDto> children = new ArrayList<>();
}
