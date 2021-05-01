package com.logistica.dtos.Organ.Structure;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StructureCreateDto extends BaseDto {
    private String name;
    private String order;
}
