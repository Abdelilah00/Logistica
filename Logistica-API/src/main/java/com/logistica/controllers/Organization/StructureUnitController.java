package com.logistica.controllers.Organization;

import com.alexy.controllers.BaseCrudController;
import com.logistica.domains.Organization.StructureUnit;
import com.logistica.dtos.Organization.StructureUnit.StructureUnitCreateDto;
import com.logistica.dtos.Organization.StructureUnit.StructureUnitDto;
import com.logistica.dtos.Organization.StructureUnit.StructureUnitUpdateDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/structureUnits")
public class StructureUnitController extends BaseCrudController<StructureUnit, StructureUnitDto, StructureUnitCreateDto, StructureUnitUpdateDto> {
}
