package com.logistica.repositories.Commands;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Commands.CommandIn;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommandInRepository extends IBaseJpaRepository<CommandIn> {
}
