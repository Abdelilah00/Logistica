package com.logistica.services.Organ.StructureUnit;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Organ.StructureUnit;
import com.logistica.dtos.Organ.StructureUnit.StructureUnitCreateDto;
import com.logistica.dtos.Organ.StructureUnit.StructureUnitDto;
import com.logistica.dtos.Organ.StructureUnit.StructureUnitUpdateDto;

public interface IStructureUnitService extends IBaseCrudService<StructureUnit, StructureUnitDto, StructureUnitCreateDto, StructureUnitUpdateDto> {

}
