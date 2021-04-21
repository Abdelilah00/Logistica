package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Products.ProductUnits;

@Repository
public interface IProductUnitsRepository extends IBaseJpaRepository<ProductUnits> {
}
