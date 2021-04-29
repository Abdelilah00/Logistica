package com.logistica.dtos.Products.TransactionDetail;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TransactionDetailCreateDto extends BaseDto {
    private Integer qte;
    private Integer article;
    private Integer lot;
    private Float priceHT;
    private Float tVA;
    private Date expDate;

    private String productName;
    private Long productId;
}
