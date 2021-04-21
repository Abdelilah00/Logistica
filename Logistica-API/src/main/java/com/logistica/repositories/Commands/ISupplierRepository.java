package com.logistica.repositories.Commands;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Commands.Supplier;

@Repository
public interface ISupplierRepository extends IBaseJpaRepository<Supplier> {
}
