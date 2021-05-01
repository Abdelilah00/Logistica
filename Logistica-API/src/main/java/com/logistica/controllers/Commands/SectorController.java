package com.logistica.controllers.Commands;

import com.alexy.controllers.BaseCrudController;
import com.logistica.domains.Commands.Sector;
import com.logistica.dtos.Commands.Sector.SectorCreateDto;
import com.logistica.dtos.Commands.Sector.SectorDto;
import com.logistica.dtos.Commands.Sector.SectorUpdateDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/sectors")
public class SectorController extends BaseCrudController<Sector, SectorDto, SectorCreateDto, SectorUpdateDto> {
}
