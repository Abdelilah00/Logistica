package com.logistica.services.Organ.Structure;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Organ.Structure;
import com.logistica.dtos.Organ.Structure.StructureCreateDto;
import com.logistica.dtos.Organ.Structure.StructureDto;
import com.logistica.dtos.Organ.Structure.StructureUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class StructureService extends BaseCrudServiceImpl<Structure, StructureDto, StructureCreateDto, StructureUpdateDto> implements IStructureService {

    public StructureService() {
        super(Structure.class, StructureDto.class, StructureCreateDto.class, StructureUpdateDto.class);
    }
}
