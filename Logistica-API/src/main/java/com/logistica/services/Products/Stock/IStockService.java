package com.logistica.services.Products.Stock;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.Stock;
import com.logistica.dtos.Products.Stock.StockCreateDto;
import com.logistica.dtos.Products.Stock.StockDto;
import com.logistica.dtos.Products.Stock.StockUpdateDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IStockService extends IBaseCrudService<Stock, StockDto, StockCreateDto, StockUpdateDto> {
    CompletableFuture<List<StockDto>> getByProductId(Long id);

}
