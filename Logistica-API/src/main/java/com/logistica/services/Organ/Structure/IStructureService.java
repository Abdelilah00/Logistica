package com.logistica.services.Organ.Structure;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Organ.Structure;
import com.logistica.dtos.Organ.Structure.StructureCreateDto;
import com.logistica.dtos.Organ.Structure.StructureDto;
import com.logistica.dtos.Organ.Structure.StructureUpdateDto;

public interface IStructureService extends IBaseCrudService<Structure, StructureDto, StructureCreateDto, StructureUpdateDto> {

}
