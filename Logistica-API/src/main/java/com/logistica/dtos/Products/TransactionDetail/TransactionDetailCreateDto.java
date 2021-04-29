package com.logistica.dtos.Products.TransactionDetail;

import com.alexy.models.BaseDto;
import com.logistica.dtos.Products.Product.ProductCreateDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TransactionDetailCreateDto extends BaseDto {
    private Integer qte;
    private Integer article;
    private Integer lot;
    private Float priceHT;
    private Float tVA;
    private ProductCreateDto productCreateDto;
}
