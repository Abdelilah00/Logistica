package com.logistica.repositories.Commands;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Commands.Needs;

@Repository
public interface INeedsRepository extends IBaseJpaRepository<Needs> {
}
