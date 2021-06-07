package com.logistica.dtos.Products.TransactionDetail;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class InputDetailDto extends BaseDto {
    private Integer qte;
    private Integer article;
    private Integer lot;
    private Date expDate;

    private String productName;
    //private InputDto ;
}

