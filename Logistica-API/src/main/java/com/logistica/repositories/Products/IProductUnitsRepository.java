package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Products.ProductUnits;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductUnitsRepository extends IBaseJpaRepository<ProductUnits> {
}
