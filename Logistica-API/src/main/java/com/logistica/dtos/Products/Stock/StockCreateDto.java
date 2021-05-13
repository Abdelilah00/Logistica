package com.logistica.dtos.Products.Stock;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockCreateDto extends BaseDto {
    private String name;
    private String adresse;
    private String area;
    private Long stockTypeId;
    private Long responsibleId;
}
