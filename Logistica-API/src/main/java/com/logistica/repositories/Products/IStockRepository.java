package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Products.Stock;
import org.springframework.stereotype.Repository;

@Repository
public interface IStockRepository extends IBaseJpaRepository<Stock> {
}
