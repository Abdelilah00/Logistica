package com.logistica.repositories.Commands;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Commands.ActorRole;
import org.springframework.stereotype.Repository;

@Repository
public interface IActorRoleRepository extends IBaseJpaRepository<ActorRole> {
}
