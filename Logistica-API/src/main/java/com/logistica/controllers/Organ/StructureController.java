package com.logistica.controllers.Organ;

import com.alexy.controllers.BaseCrudController;
import com.logistica.domains.Organ.Structure;
import com.logistica.dtos.Organ.Structure.StructureCreateDto;
import com.logistica.dtos.Organ.Structure.StructureDto;
import com.logistica.dtos.Organ.Structure.StructureUpdateDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/structures")
public class StructureController extends BaseCrudController<Structure, StructureDto, StructureCreateDto, StructureUpdateDto> {
}
