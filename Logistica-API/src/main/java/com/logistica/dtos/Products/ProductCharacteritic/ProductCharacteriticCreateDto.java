package com.logistica.dtos.Products.ProductCharacteritic;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCharacteriticCreateDto extends BaseDto {
private String value;
private String Product;
private String Characteristic;
}
