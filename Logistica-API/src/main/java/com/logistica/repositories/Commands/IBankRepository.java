package com.logistica.repositories.Commands;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Commands.Bank;

@Repository
public interface IBankRepository extends IBaseJpaRepository<Bank> {
}
