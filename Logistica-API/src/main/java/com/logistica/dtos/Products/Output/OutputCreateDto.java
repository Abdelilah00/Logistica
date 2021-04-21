package com.logistica.dtos.Products.Output;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutputCreateDto extends BaseDto {
private String distination;
private String askBy;
private String DistinationType;
}
