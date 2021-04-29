package com.logistica.dtos.Products.Product;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProductDto extends BaseDto {
    private String name;
    private String categoryName;
    private Integer categoryId;
    private Float tVA;
    private Integer stockMin;
    private Integer stockMax;
    private Integer stockSecurity;
}
