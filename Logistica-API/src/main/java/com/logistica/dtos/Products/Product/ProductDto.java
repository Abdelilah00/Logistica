package com.logistica.dtos.Products.Product;

import com.alexy.models.BaseDto;
import com.logistica.dtos.Products.Category.CategoryDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto extends BaseDto {
    private String name;
    private String categoryName;

    private Integer stockMin;
    private Integer stockMax;
    private Integer stockSecurity;
    private Float priceHT;
    private Float tva;

    private Integer qteByStock;
    private Integer qteInStocks;
}
