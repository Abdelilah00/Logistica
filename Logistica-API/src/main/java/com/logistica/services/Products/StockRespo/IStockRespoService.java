package com.logistica.services.Products.StockRespo;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.StockRespo;
import com.logistica.dtos.Products.StockRespo.StockRespoCreateDto;
import com.logistica.dtos.Products.StockRespo.StockRespoDto;
import com.logistica.dtos.Products.StockRespo.StockRespoUpdateDto;

public interface IStockRespoService extends IBaseCrudService<StockRespo, StockRespoDto, StockRespoCreateDto, StockRespoUpdateDto> {

}
