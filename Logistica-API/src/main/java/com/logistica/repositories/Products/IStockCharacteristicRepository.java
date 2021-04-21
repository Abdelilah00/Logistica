package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Products.StockCharacteristic;

@Repository
public interface IStockCharacteristicRepository extends IBaseJpaRepository<StockCharacteristic> {
}
