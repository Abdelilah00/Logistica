package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Products.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends IBaseJpaRepository<Category> {
    List<Category> getByParentId(Long id);

    List<Category> getByParentIsNull();

    List<Category> getByParentIsNotNull();
}
