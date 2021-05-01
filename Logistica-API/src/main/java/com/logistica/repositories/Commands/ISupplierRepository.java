package com.logistica.repositories.Commands;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Commands.Supplier;
import org.springframework.stereotype.Repository;

@Repository
public interface ISupplierRepository extends IBaseJpaRepository<Supplier> {
}
