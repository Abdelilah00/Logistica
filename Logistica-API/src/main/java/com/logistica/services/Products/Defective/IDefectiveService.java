package com.logistica.services.Products.Defective;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.Defective;
import com.logistica.dtos.Products.Defective.DefectiveCreateDto;
import com.logistica.dtos.Products.Defective.DefectiveDto;
import com.logistica.dtos.Products.Defective.DefectiveUpdateDto;
import org.springframework.stereotype.Service;

public interface IDefectiveService extends IBaseCrudService<Defective, DefectiveDto, DefectiveCreateDto, DefectiveUpdateDto> {

}
