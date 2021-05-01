package com.logistica.services.Commands.Sector;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Commands.Sector;
import com.logistica.dtos.Commands.Sector.SectorCreateDto;
import com.logistica.dtos.Commands.Sector.SectorDto;
import com.logistica.dtos.Commands.Sector.SectorUpdateDto;

public interface ISectorService extends IBaseCrudService<Sector, SectorDto, SectorCreateDto, SectorUpdateDto> {

}
