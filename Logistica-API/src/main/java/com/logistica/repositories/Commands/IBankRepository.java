package com.logistica.repositories.Commands;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Commands.Bank;
import org.springframework.stereotype.Repository;

@Repository
public interface IBankRepository extends IBaseJpaRepository<Bank> {
}
