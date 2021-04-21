package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Products.ProductCharacteritic;

@Repository
public interface IProductCharacteriticRepository extends IBaseJpaRepository<ProductCharacteritic> {
}
