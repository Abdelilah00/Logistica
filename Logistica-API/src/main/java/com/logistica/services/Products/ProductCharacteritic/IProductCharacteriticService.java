package com.logistica.services.Products.ProductCharacteritic;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.ProductCharacteritic;
import com.logistica.dtos.Products.ProductCharacteritic.ProductCharacteriticCreateDto;
import com.logistica.dtos.Products.ProductCharacteritic.ProductCharacteriticDto;
import com.logistica.dtos.Products.ProductCharacteritic.ProductCharacteriticUpdateDto;

public interface IProductCharacteriticService extends IBaseCrudService<ProductCharacteritic, ProductCharacteriticDto, ProductCharacteriticCreateDto, ProductCharacteriticUpdateDto> {

}
