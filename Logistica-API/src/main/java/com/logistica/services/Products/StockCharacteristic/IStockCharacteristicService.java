package com.logistica.services.Products.StockCharacteristic;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.StockCharacteristic;
import com.logistica.dtos.Products.StockCharacteristic.StockCharacteristicCreateDto;
import com.logistica.dtos.Products.StockCharacteristic.StockCharacteristicDto;
import com.logistica.dtos.Products.StockCharacteristic.StockCharacteristicUpdateDto;
import org.springframework.stereotype.Service;

public interface IStockCharacteristicService extends IBaseCrudService<StockCharacteristic, StockCharacteristicDto, StockCharacteristicCreateDto, StockCharacteristicUpdateDto> {

}
