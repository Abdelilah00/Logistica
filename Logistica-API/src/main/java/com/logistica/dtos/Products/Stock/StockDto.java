package com.logistica.dtos.Products.Stock;

import com.alexy.models.BaseDto;
import com.logistica.dtos.Products.StockRespo.StockRespoDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockDto extends BaseDto {
    private String name;
    private String adresse;
    private String area;
    private String responsibleName;
    private String type;
}
