package com.logistica.dtos.Products.Product;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProductCreateDto extends BaseDto {
    private String name;
    private Integer CategoryId;
    private Date expDate;
    private Integer stockMin;
    private Integer stockMax;
    private Integer stockSecurity;
    //private String Parent;
}
