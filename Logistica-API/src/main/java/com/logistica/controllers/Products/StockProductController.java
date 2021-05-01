package com.logistica.controllers.Products;

import com.alexy.controllers.BaseCrudController;
import com.logistica.domains.Products.StockProduct;
import com.logistica.dtos.Products.StockProduct.StockProductCreateDto;
import com.logistica.dtos.Products.StockProduct.StockProductDto;
import com.logistica.dtos.Products.StockProduct.StockProductUpdateDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/stockproducts")
public class StockProductController extends BaseCrudController<StockProduct, StockProductDto, StockProductCreateDto, StockProductUpdateDto> {
}
