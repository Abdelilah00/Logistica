package com.logistica.services.Products.Stock;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Products.Stock;
import com.logistica.dtos.Products.Stock.StockCreateDto;
import com.logistica.dtos.Products.Stock.StockDto;
import com.logistica.dtos.Products.Stock.StockUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class StockService extends BaseCrudServiceImpl<Stock, StockDto, StockCreateDto, StockUpdateDto> implements IStockService {

    public StockService() {
        super(Stock.class, StockDto.class, StockCreateDto.class, StockUpdateDto.class);
    }
}
