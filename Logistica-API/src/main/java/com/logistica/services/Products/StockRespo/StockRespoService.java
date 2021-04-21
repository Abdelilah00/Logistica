package com.logistica.services.Products.StockRespo;

import com.alexy.services.BaseCrudServiceImpl;
import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.StockRespo;
import com.logistica.dtos.Products.StockRespo.StockRespoCreateDto;
import com.logistica.dtos.Products.StockRespo.StockRespoDto;
import com.logistica.dtos.Products.StockRespo.StockRespoUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class StockRespoService extends BaseCrudServiceImpl<StockRespo, StockRespoDto, StockRespoCreateDto, StockRespoUpdateDto> implements IStockRespoService {

    public StockRespoService() {
        super(StockRespo.class, StockRespoDto.class, StockRespoCreateDto.class, StockRespoUpdateDto.class);
    }
}
