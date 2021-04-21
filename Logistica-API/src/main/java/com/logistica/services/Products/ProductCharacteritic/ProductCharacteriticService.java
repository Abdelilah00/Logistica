package com.logistica.services.Products.ProductCharacteritic;

import com.alexy.services.BaseCrudServiceImpl;
import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.ProductCharacteritic;
import com.logistica.dtos.Products.ProductCharacteritic.ProductCharacteriticCreateDto;
import com.logistica.dtos.Products.ProductCharacteritic.ProductCharacteriticDto;
import com.logistica.dtos.Products.ProductCharacteritic.ProductCharacteriticUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class ProductCharacteriticService extends BaseCrudServiceImpl<ProductCharacteritic, ProductCharacteriticDto, ProductCharacteriticCreateDto, ProductCharacteriticUpdateDto> implements IProductCharacteriticService {

    public ProductCharacteriticService() {
        super(ProductCharacteritic.class, ProductCharacteriticDto.class, ProductCharacteriticCreateDto.class, ProductCharacteriticUpdateDto.class);
    }
}
