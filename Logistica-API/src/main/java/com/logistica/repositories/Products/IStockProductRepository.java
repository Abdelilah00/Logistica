package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Products.StockProduct;
import org.springframework.stereotype.Repository;

@Repository
public interface IStockProductRepository extends IBaseJpaRepository<StockProduct> {
    Boolean existsByProductIdAndStockDefIsTrue(Long productId);

    StockProduct findByProductIdAndStockDefIsTrue(Long productId);

    StockProduct findByProductIdAndStockId(Long productId, Long stockId);
}
