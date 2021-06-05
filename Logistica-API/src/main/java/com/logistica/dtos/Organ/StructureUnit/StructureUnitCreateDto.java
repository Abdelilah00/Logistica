package com.logistica.dtos.Organ.StructureUnit;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StructureUnitCreateDto extends BaseDto {
    private String name;
    private String Structure;
}
