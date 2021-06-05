package com.logistica.services.Organ.StructureUnit;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Organ.StructureUnit;
import com.logistica.dtos.Organ.StructureUnit.StructureUnitCreateDto;
import com.logistica.dtos.Organ.StructureUnit.StructureUnitDto;
import com.logistica.dtos.Organ.StructureUnit.StructureUnitUpdateDto;

@org.springframework.stereotype.Service
public class StructureUnitService extends BaseCrudServiceImpl<StructureUnit, StructureUnitDto, StructureUnitCreateDto, StructureUnitUpdateDto> implements IStructureUnitService {

    public StructureUnitService() {
        super(StructureUnit.class, StructureUnitDto.class, StructureUnitCreateDto.class, StructureUnitUpdateDto.class);
    }
}
