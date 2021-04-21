package com.logistica.controllers.Products;

import com.alexy.controllers.BaseCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.logistica.domains.Products.ProductCharacteritic;
import com.logistica.dtos.Products.ProductCharacteritic.ProductCharacteriticCreateDto;
import com.logistica.dtos.Products.ProductCharacteritic.ProductCharacteriticDto;
import com.logistica.dtos.Products.ProductCharacteritic.ProductCharacteriticUpdateDto;

@RestController
@RequestMapping("api/productcharacteritics")
public class ProductCharacteriticController extends BaseCrudController<ProductCharacteritic, ProductCharacteriticDto, ProductCharacteriticCreateDto, ProductCharacteriticUpdateDto> {
}
