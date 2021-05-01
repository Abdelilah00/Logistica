package com.logistica.dtos.Commands.Sector;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SectorCreateDto extends BaseDto {
    private String name;
}
