package com.logistica.dtos.Products.TransactionDetail;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDetailUpdateDto extends BaseDto {
    private String qte;
    private String article;
    private String lot;
    private Float priceHT;
    private Float tVA;
    private String Product;
    private String Input;
}
