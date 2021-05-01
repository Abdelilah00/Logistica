package com.logistica.services.Products.Output;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Products.Output;
import com.logistica.dtos.Products.Output.OutputCreateDto;
import com.logistica.dtos.Products.Output.OutputDto;
import com.logistica.dtos.Products.Output.OutputUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class OutputService extends BaseCrudServiceImpl<Output, OutputDto, OutputCreateDto, OutputUpdateDto> implements IOutputService {

    public OutputService() {
        super(Output.class, OutputDto.class, OutputCreateDto.class, OutputUpdateDto.class);
    }
}
