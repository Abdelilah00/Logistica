package com.logistica.dtos.Products.Input;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputCreateDto extends BaseDto {
private String acteur;
private String ActeurType;
private String Stage;
}
