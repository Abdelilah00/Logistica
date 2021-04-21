package com.logistica.services.Products.StockCharacteristic;

import com.alexy.services.BaseCrudServiceImpl;
import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.StockCharacteristic;
import com.logistica.dtos.Products.StockCharacteristic.StockCharacteristicCreateDto;
import com.logistica.dtos.Products.StockCharacteristic.StockCharacteristicDto;
import com.logistica.dtos.Products.StockCharacteristic.StockCharacteristicUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class StockCharacteristicService extends BaseCrudServiceImpl<StockCharacteristic, StockCharacteristicDto, StockCharacteristicCreateDto, StockCharacteristicUpdateDto> implements IStockCharacteristicService {

    public StockCharacteristicService() {
        super(StockCharacteristic.class, StockCharacteristicDto.class, StockCharacteristicCreateDto.class, StockCharacteristicUpdateDto.class);
    }
}
