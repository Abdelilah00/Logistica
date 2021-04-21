package com.logistica.dtos.Products.Cosmetic;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CosmeticUpdateDto extends BaseDto {
private String Product;
private String expDate;
}
