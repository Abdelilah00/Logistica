package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Products.ProductCharacteritic;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductCharacteriticRepository extends IBaseJpaRepository<ProductCharacteritic> {
}
