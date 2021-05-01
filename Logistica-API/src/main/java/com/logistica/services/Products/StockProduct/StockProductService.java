package com.logistica.services.Products.StockProduct;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Products.StockProduct;
import com.logistica.dtos.Products.StockProduct.StockProductCreateDto;
import com.logistica.dtos.Products.StockProduct.StockProductDto;
import com.logistica.dtos.Products.StockProduct.StockProductUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class StockProductService extends BaseCrudServiceImpl<StockProduct, StockProductDto, StockProductCreateDto, StockProductUpdateDto> implements IStockProductService {

    public StockProductService() {
        super(StockProduct.class, StockProductDto.class, StockProductCreateDto.class, StockProductUpdateDto.class);
    }
}
