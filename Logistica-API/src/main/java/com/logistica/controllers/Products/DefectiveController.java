package com.logistica.controllers.Products;

import com.alexy.controllers.BaseCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.logistica.domains.Products.Defective;
import com.logistica.dtos.Products.Defective.DefectiveCreateDto;
import com.logistica.dtos.Products.Defective.DefectiveDto;
import com.logistica.dtos.Products.Defective.DefectiveUpdateDto;

@RestController
@RequestMapping("api/defectives")
public class DefectiveController extends BaseCrudController<Defective, DefectiveDto, DefectiveCreateDto, DefectiveUpdateDto> {
}
