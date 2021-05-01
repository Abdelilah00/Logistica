package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Products.Output;
import org.springframework.stereotype.Repository;

@Repository
public interface IOutputRepository extends IBaseJpaRepository<Output> {
}
