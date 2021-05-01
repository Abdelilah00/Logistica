package com.logistica.controllers.Products;

import com.alexy.controllers.BaseCrudController;
import com.logistica.domains.Products.Stock;
import com.logistica.dtos.Products.Stock.StockCreateDto;
import com.logistica.dtos.Products.Stock.StockDto;
import com.logistica.dtos.Products.Stock.StockUpdateDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/stocks")
public class StockController extends BaseCrudController<Stock, StockDto, StockCreateDto, StockUpdateDto> {
}
