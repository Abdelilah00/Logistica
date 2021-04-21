package com.logistica.services.Products.StockProduct;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.StockProduct;
import com.logistica.dtos.Products.StockProduct.StockProductCreateDto;
import com.logistica.dtos.Products.StockProduct.StockProductDto;
import com.logistica.dtos.Products.StockProduct.StockProductUpdateDto;
import org.springframework.stereotype.Service;

public interface IStockProductService extends IBaseCrudService<StockProduct, StockProductDto, StockProductCreateDto, StockProductUpdateDto> {

}
