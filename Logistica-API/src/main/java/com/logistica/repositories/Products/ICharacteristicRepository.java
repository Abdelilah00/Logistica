package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Products.Characteristic;
import org.springframework.stereotype.Repository;

@Repository
public interface ICharacteristicRepository extends IBaseJpaRepository<Characteristic> {
}
