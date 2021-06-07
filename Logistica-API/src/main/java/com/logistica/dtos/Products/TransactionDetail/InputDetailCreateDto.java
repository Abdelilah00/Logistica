package com.logistica.dtos.Products.TransactionDetail;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class InputDetailCreateDto extends BaseDto {
    private Integer article;
    private Integer lot;
    private Date expDate;
    private Integer qte;

    private String productName;
    private Long productId;

    private Long stockId;
}
