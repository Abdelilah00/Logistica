package com.logistica.dtos.Products.TransactionDetail;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OutputDetailUpdateDto extends BaseDto {
    private String qte;
    private String article;
    private String lot;
    private Float priceHT;
    private Float tVA;
    private Date expDate;

    private String Product;
    private String Input;
}
