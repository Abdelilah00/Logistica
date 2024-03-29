package com.logistica.controllers.Products;

import com.alexy.controllers.BaseCrudController;
import com.logistica.domains.Products.Stock;
import com.logistica.dtos.Products.Stock.StockCreateDto;
import com.logistica.dtos.Products.Stock.StockDto;
import com.logistica.dtos.Products.Stock.StockUpdateDto;
import com.logistica.services.Products.Stock.IStockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/stocks")
public class StockController extends BaseCrudController<Stock, StockDto, StockCreateDto, StockUpdateDto> {
    @GetMapping(path = "/getByProductId/{id}")
    protected List<StockDto> getByProductId(@PathVariable(value = "id") Long id) throws ExecutionException, InterruptedException {
        return ((IStockService) service).getByProductId(id).get();
    }
}
