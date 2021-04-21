package com.logistica.dtos.Products.StockCharacteristic;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockCharacteristicDto extends BaseDto {
private String value;
private String Stock;
private String Characteristic;
}
