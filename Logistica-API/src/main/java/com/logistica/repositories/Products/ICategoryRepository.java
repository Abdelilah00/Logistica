package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Products.Category;

@Repository
public interface ICategoryRepository extends IBaseJpaRepository<Category> {
}
