package com.logistica.dtos.Products.TransactionDetail;

import com.alexy.models.BaseDto;
import com.logistica.dtos.Products.Input.InputDto;
import com.logistica.dtos.Products.Product.ProductDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TransactionDetailDto extends BaseDto {
    private Integer qte;
    private Integer article;
    private Integer lot;
    private Float priceHT;
    private Float tVA;
    private Date expDate;

    private String productName;
    //private InputDto ;
}
