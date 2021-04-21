package com.logistica.repositories.Commands;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Commands.CommandOut;

@Repository
public interface ICommandOutRepository extends IBaseJpaRepository<CommandOut> {
}
