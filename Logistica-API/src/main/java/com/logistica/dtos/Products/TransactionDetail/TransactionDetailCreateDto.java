package com.logistica.dtos.Products.TransactionDetail;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDetailCreateDto extends BaseDto {
private String qte;
private String article;
private String lot;
private String Product;
private String Input;
}
