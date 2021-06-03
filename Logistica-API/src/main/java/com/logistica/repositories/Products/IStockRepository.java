package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Products.Stock;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStockRepository extends IBaseJpaRepository<Stock> {
    List<Stock> getByStockProductsProductId(Long id);

    Stock getStockByDefIsTrue();
}
