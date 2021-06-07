package com.logistica.services.Organization.StructureUnit;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Organization.StructureUnit;
import com.logistica.dtos.Organization.StructureUnit.StructureUnitCreateDto;
import com.logistica.dtos.Organization.StructureUnit.StructureUnitDto;
import com.logistica.dtos.Organization.StructureUnit.StructureUnitUpdateDto;

public interface IStructureUnitService extends IBaseCrudService<StructureUnit, StructureUnitDto, StructureUnitCreateDto, StructureUnitUpdateDto> {

}
