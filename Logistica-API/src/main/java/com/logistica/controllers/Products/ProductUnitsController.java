package com.logistica.controllers.Products;

import com.alexy.controllers.BaseCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.logistica.domains.Products.ProductUnits;
import com.logistica.dtos.Products.ProductUnits.ProductUnitsCreateDto;
import com.logistica.dtos.Products.ProductUnits.ProductUnitsDto;
import com.logistica.dtos.Products.ProductUnits.ProductUnitsUpdateDto;

@RestController
@RequestMapping("api/productUnits")
public class ProductUnitsController extends BaseCrudController<ProductUnits, ProductUnitsDto, ProductUnitsCreateDto, ProductUnitsUpdateDto> {
}
