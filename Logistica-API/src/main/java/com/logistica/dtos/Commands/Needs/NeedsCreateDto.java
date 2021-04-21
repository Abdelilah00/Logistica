package com.logistica.dtos.Commands.Needs;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NeedsCreateDto extends BaseDto {
private String qte;
private String Product;
}
