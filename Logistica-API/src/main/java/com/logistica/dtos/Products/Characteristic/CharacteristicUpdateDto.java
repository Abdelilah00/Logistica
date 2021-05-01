package com.logistica.dtos.Products.Characteristic;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacteristicUpdateDto extends BaseDto {
    private String name;
}
