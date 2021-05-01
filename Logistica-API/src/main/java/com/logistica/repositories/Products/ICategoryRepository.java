package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Products.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends IBaseJpaRepository<Category> {
}
