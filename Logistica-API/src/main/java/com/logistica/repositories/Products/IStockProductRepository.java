package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Products.StockProduct;

@Repository
public interface IStockProductRepository extends IBaseJpaRepository<StockProduct> {
}
