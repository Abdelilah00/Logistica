package com.logistica.services.Products.Stock;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.Stock;
import com.logistica.dtos.Products.Stock.StockCreateDto;
import com.logistica.dtos.Products.Stock.StockDto;
import com.logistica.dtos.Products.Stock.StockUpdateDto;
import org.springframework.stereotype.Service;

public interface IStockService extends IBaseCrudService<Stock, StockDto, StockCreateDto, StockUpdateDto> {

}
