package com.logistica.services.Products.Output;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.Output;
import com.logistica.dtos.Products.Output.OutputCreateDto;
import com.logistica.dtos.Products.Output.OutputDto;
import com.logistica.dtos.Products.Output.OutputUpdateDto;
import org.springframework.stereotype.Service;

public interface IOutputService extends IBaseCrudService<Output, OutputDto, OutputCreateDto, OutputUpdateDto> {

}
