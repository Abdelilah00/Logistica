package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Products.Input;
import org.springframework.stereotype.Repository;

@Repository
public interface IInputRepository extends IBaseJpaRepository<Input> {
}
