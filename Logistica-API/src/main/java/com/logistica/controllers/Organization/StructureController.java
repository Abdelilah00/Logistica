package com.logistica.controllers.Organization;

import com.alexy.controllers.BaseCrudController;
import com.logistica.domains.Organization.Structure;
import com.logistica.dtos.Organization.Structure.StructureCreateDto;
import com.logistica.dtos.Organization.Structure.StructureDto;
import com.logistica.dtos.Organization.Structure.StructureUpdateDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/structures")
public class StructureController extends BaseCrudController<Structure, StructureDto, StructureCreateDto, StructureUpdateDto> {
}
