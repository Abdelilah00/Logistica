package com.logistica.services.Organization.StructureUnit;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Organization.StructureUnit;
import com.logistica.dtos.Organization.StructureUnit.StructureUnitCreateDto;
import com.logistica.dtos.Organization.StructureUnit.StructureUnitDto;
import com.logistica.dtos.Organization.StructureUnit.StructureUnitUpdateDto;

@org.springframework.stereotype.Service
public class StructureUnitService extends BaseCrudServiceImpl<StructureUnit, StructureUnitDto, StructureUnitCreateDto, StructureUnitUpdateDto> implements IStructureUnitService {

    public StructureUnitService() {
        super(StructureUnit.class, StructureUnitDto.class, StructureUnitCreateDto.class, StructureUnitUpdateDto.class);
    }
}
