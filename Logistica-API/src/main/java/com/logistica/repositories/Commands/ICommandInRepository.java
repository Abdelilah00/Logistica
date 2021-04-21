package com.logistica.repositories.Commands;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Commands.CommandIn;

@Repository
public interface ICommandInRepository extends IBaseJpaRepository<CommandIn> {
}
