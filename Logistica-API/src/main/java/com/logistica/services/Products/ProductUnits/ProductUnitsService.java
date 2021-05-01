package com.logistica.services.Products.ProductUnits;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Products.ProductUnits;
import com.logistica.dtos.Products.ProductUnits.ProductUnitsCreateDto;
import com.logistica.dtos.Products.ProductUnits.ProductUnitsDto;
import com.logistica.dtos.Products.ProductUnits.ProductUnitsUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class ProductUnitsService extends BaseCrudServiceImpl<ProductUnits, ProductUnitsDto, ProductUnitsCreateDto, ProductUnitsUpdateDto> implements IProductUnitsService {

    public ProductUnitsService() {
        super(ProductUnits.class, ProductUnitsDto.class, ProductUnitsCreateDto.class, ProductUnitsUpdateDto.class);
    }
}
