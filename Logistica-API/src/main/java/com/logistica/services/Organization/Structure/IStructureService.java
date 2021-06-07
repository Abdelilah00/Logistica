package com.logistica.services.Organization.Structure;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Organization.Structure;
import com.logistica.dtos.Organization.Structure.StructureCreateDto;
import com.logistica.dtos.Organization.Structure.StructureDto;
import com.logistica.dtos.Organization.Structure.StructureUpdateDto;

public interface IStructureService extends IBaseCrudService<Structure, StructureDto, StructureCreateDto, StructureUpdateDto> {

}
