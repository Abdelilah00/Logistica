package com.logistica.services.Commands.Sector;

import com.alexy.services.BaseCrudServiceImpl;
import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Commands.Sector;
import com.logistica.dtos.Commands.Sector.SectorCreateDto;
import com.logistica.dtos.Commands.Sector.SectorDto;
import com.logistica.dtos.Commands.Sector.SectorUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class SectorService extends BaseCrudServiceImpl<Sector, SectorDto, SectorCreateDto, SectorUpdateDto> implements ISectorService {

    public SectorService() {
        super(Sector.class, SectorDto.class, SectorCreateDto.class, SectorUpdateDto.class);
    }
}
