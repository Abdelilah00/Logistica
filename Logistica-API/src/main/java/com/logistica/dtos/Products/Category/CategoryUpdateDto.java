package com.logistica.dtos.Products.Category;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryUpdateDto extends BaseDto {
    private String name;
    private Float defaultTva;

    private Long defaultStockMin;
    private Long defaultStockMax;
    private Long defaultStockSecurity;
}
