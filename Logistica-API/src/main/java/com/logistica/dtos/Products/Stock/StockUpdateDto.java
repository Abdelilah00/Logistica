package com.logistica.dtos.Products.Stock;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockUpdateDto extends BaseDto {
private String adresse;
private String area;
private String StockType;
private String StockRespo;
}
