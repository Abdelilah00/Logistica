package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Products.Input;

@Repository
public interface IInputRepository extends IBaseJpaRepository<Input> {
}
