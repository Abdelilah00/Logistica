package com.logistica.controllers.Products;

import com.alexy.controllers.BaseCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.logistica.domains.Products.Output;
import com.logistica.dtos.Products.Output.OutputCreateDto;
import com.logistica.dtos.Products.Output.OutputDto;
import com.logistica.dtos.Products.Output.OutputUpdateDto;

@RestController
@RequestMapping("api/outputs")
public class OutputController extends BaseCrudController<Output, OutputDto, OutputCreateDto, OutputUpdateDto> {
}
