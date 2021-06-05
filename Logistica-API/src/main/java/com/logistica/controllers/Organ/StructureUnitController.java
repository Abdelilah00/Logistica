package com.logistica.controllers.Organ;

import com.alexy.controllers.BaseCrudController;
import com.logistica.domains.Organ.StructureUnit;
import com.logistica.dtos.Organ.StructureUnit.StructureUnitCreateDto;
import com.logistica.dtos.Organ.StructureUnit.StructureUnitDto;
import com.logistica.dtos.Organ.StructureUnit.StructureUnitUpdateDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/structureUnits")
public class StructureUnitController extends BaseCrudController<StructureUnit, StructureUnitDto, StructureUnitCreateDto, StructureUnitUpdateDto> {
}
