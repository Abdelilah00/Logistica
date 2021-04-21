package com.logistica.services.Products.Defective;

import com.alexy.services.BaseCrudServiceImpl;
import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.Defective;
import com.logistica.dtos.Products.Defective.DefectiveCreateDto;
import com.logistica.dtos.Products.Defective.DefectiveDto;
import com.logistica.dtos.Products.Defective.DefectiveUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class DefectiveService extends BaseCrudServiceImpl<Defective, DefectiveDto, DefectiveCreateDto, DefectiveUpdateDto> implements IDefectiveService {

    public DefectiveService() {
        super(Defective.class, DefectiveDto.class, DefectiveCreateDto.class, DefectiveUpdateDto.class);
    }
}
