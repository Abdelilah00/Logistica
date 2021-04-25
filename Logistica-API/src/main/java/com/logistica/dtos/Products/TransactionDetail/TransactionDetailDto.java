package com.logistica.dtos.Products.TransactionDetail;

import com.alexy.models.BaseDto;
import com.logistica.dtos.Products.Input.InputDto;
import com.logistica.dtos.Products.Product.ProductDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDetailDto extends BaseDto {
    private String qte;
    private String article;
    private String lot;
    private ProductDto productDto;
    //private InputDto ;
}
