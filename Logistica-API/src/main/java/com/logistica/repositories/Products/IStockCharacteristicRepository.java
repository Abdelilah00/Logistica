package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Products.StockCharacteristic;
import org.springframework.stereotype.Repository;

@Repository
public interface IStockCharacteristicRepository extends IBaseJpaRepository<StockCharacteristic> {
}
