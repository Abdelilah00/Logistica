package com.logistica.services.Products.Stock;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Products.Stock;
import com.logistica.dtos.Products.Stock.StockCreateDto;
import com.logistica.dtos.Products.Stock.StockDto;
import com.logistica.dtos.Products.Stock.StockUpdateDto;
import com.logistica.repositories.Products.IStockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class StockService extends BaseCrudServiceImpl<Stock, StockDto, StockCreateDto, StockUpdateDto> implements IStockService {

    public StockService() {
        super(Stock.class, StockDto.class, StockCreateDto.class, StockUpdateDto.class);
    }

    @Override
    public CompletableFuture<List<StockDto>> getByProductId(Long id) {
        var tmp = ((IStockRepository) repository).getByStockProductsProductId(id);
        List<StockDto> tmpDto = objectMapper.convertToDtoList(tmp, StockDto.class);
        for (int i = 0; i < tmp.size(); i++) {
            tmpDto.get(i).setQteByProduct(tmp.get(i).getStockProducts().stream().filter(f -> f.getProduct().getId() == id).collect(Collectors.toList()).get(0).getQte());
        }
        return CompletableFuture.completedFuture(tmpDto);
    }
}
