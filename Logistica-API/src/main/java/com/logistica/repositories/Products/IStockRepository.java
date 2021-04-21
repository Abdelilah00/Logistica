package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Products.Stock;

@Repository
public interface IStockRepository extends IBaseJpaRepository<Stock> {
}
