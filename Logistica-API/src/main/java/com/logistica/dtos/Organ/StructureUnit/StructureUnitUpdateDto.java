package com.logistica.dtos.Organ.StructureUnit;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StructureUnitUpdateDto extends BaseDto {
    private String name;
    private String Structure;
}
