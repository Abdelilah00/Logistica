package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Products.Product;

@Repository
public interface IProductRepository extends IBaseJpaRepository<Product> {
}
