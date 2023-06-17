package com.logistica.dtos.Products.Product;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreateDto extends BaseDto {
    private String name;
    private Integer categoryId;

    private Integer stockMin;
    private Integer stockMax;
    private Integer stockSecurity;

    private Float priceHT;
    private Float tva;
    //private String Parent;
}
