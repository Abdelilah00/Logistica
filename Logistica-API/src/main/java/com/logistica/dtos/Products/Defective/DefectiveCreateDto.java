package com.logistica.dtos.Products.Defective;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefectiveCreateDto extends BaseDto {
private String Product;
private String qte;
}
