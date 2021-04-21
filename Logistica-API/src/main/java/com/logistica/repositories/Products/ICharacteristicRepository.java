package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Products.Characteristic;

@Repository
public interface ICharacteristicRepository extends IBaseJpaRepository<Characteristic> {
}
