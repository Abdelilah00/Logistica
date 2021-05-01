package com.logistica.repositories.Commands;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Commands.CommandOut;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommandOutRepository extends IBaseJpaRepository<CommandOut> {
}
