package com.logistica.controllers.Products;

import com.alexy.controllers.BaseCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.logistica.domains.Products.StockRespo;
import com.logistica.dtos.Products.StockRespo.StockRespoCreateDto;
import com.logistica.dtos.Products.StockRespo.StockRespoDto;
import com.logistica.dtos.Products.StockRespo.StockRespoUpdateDto;

@RestController
@RequestMapping("api/stockrespos")
public class StockRespoController extends BaseCrudController<StockRespo, StockRespoDto, StockRespoCreateDto, StockRespoUpdateDto> {
}
