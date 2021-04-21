package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Products.Defective;

@Repository
public interface IDefectiveRepository extends IBaseJpaRepository<Defective> {
}
