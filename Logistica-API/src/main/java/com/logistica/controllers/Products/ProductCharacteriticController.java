package com.logistica.controllers.Products;

import com.alexy.controllers.BaseCrudController;
import com.logistica.domains.Products.ProductCharacteritic;
import com.logistica.dtos.Products.ProductCharacteritic.ProductCharacteriticCreateDto;
import com.logistica.dtos.Products.ProductCharacteritic.ProductCharacteriticDto;
import com.logistica.dtos.Products.ProductCharacteritic.ProductCharacteriticUpdateDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/productCharacteritics")
public class ProductCharacteriticController extends BaseCrudController<ProductCharacteritic, ProductCharacteriticDto, ProductCharacteriticCreateDto, ProductCharacteriticUpdateDto> {
}
