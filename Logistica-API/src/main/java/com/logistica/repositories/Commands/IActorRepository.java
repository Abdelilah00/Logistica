package com.logistica.repositories.Commands;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Commands.Actor;
import org.springframework.stereotype.Repository;

@Repository
public interface IActorRepository extends IBaseJpaRepository<Actor> {
}
