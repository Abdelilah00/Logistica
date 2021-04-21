package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Products.StockRespo;

@Repository
public interface IStockRespoRepository extends IBaseJpaRepository<StockRespo> {
}
