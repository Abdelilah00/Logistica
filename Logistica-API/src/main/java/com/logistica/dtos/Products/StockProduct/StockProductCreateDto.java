package com.logistica.dtos.Products.StockProduct;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockProductCreateDto extends BaseDto {
    private String qte;
    private String Product;
    private String Stock;
}
