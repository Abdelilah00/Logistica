package com.logistica.repositories.Commands;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Commands.ActorType;
import org.springframework.stereotype.Repository;

@Repository
public interface IActorRoleRepository extends IBaseJpaRepository<ActorType> {
}
