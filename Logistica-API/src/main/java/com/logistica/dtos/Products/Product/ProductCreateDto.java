package com.logistica.dtos.Products.Product;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreateDto extends BaseDto {
private String name;
private String priceHT;
private String expDate;
private String tVA;
private String Category;
private String Parent;
}
