package com.logistica.dtos.Products.Product;

import com.alexy.models.BaseDto;
import com.logistica.dtos.Products.Category.CategoryDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto extends BaseDto {
    private String name;
    private CategoryDto category;

    private Integer stockMin;
    private Integer stockMax;
    private Integer stockSecurity;

    private Integer qteByStock;
    private Integer qteInStocks;
}
