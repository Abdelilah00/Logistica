package com.logistica.dtos.Organization.StructureUnit;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StructureUnitCreateDto extends BaseDto {
    private String name;
    private Long structureId;
}
