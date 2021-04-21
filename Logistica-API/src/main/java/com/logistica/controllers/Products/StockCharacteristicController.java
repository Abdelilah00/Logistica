package com.logistica.controllers.Products;

import com.alexy.controllers.BaseCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.logistica.domains.Products.StockCharacteristic;
import com.logistica.dtos.Products.StockCharacteristic.StockCharacteristicCreateDto;
import com.logistica.dtos.Products.StockCharacteristic.StockCharacteristicDto;
import com.logistica.dtos.Products.StockCharacteristic.StockCharacteristicUpdateDto;

@RestController
@RequestMapping("api/stockcharacteristics")
public class StockCharacteristicController extends BaseCrudController<StockCharacteristic, StockCharacteristicDto, StockCharacteristicCreateDto, StockCharacteristicUpdateDto> {
}
