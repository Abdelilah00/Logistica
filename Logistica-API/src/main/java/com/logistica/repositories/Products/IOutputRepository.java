package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Products.Output;

@Repository
public interface IOutputRepository extends IBaseJpaRepository<Output> {
}
