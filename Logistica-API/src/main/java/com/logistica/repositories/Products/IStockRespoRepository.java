package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Products.StockRespo;
import org.springframework.stereotype.Repository;

@Repository
public interface IStockRespoRepository extends IBaseJpaRepository<StockRespo> {
}
