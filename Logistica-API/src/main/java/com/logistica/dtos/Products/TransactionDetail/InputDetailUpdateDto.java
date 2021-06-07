package com.logistica.dtos.Products.TransactionDetail;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class InputDetailUpdateDto extends BaseDto {
    private String qte;
    private String article;
    private String lot;
    private Float priceHT;
    private Date expDate;

    private String Product;
    private String Input;
}
