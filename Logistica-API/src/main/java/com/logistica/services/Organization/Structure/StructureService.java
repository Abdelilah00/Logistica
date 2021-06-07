package com.logistica.services.Organization.Structure;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Organization.Structure;
import com.logistica.dtos.Organization.Structure.StructureCreateDto;
import com.logistica.dtos.Organization.Structure.StructureDto;
import com.logistica.dtos.Organization.Structure.StructureUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class StructureService extends BaseCrudServiceImpl<Structure, StructureDto, StructureCreateDto, StructureUpdateDto> implements IStructureService {

    public StructureService() {
        super(Structure.class, StructureDto.class, StructureCreateDto.class, StructureUpdateDto.class);
    }
}
