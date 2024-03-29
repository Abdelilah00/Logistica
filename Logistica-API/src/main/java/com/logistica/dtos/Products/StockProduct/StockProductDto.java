package com.logistica.dtos.Products.StockProduct;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockProductDto extends BaseDto {
    private String qte;
    private String productName;
    private String stockName;
}
