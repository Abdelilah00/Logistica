package com.logistica.services.Products.ProductUnits;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.ProductUnits;
import com.logistica.dtos.Products.ProductUnits.ProductUnitsCreateDto;
import com.logistica.dtos.Products.ProductUnits.ProductUnitsDto;
import com.logistica.dtos.Products.ProductUnits.ProductUnitsUpdateDto;

public interface IProductUnitsService extends IBaseCrudService<ProductUnits, ProductUnitsDto, ProductUnitsCreateDto, ProductUnitsUpdateDto> {

}
