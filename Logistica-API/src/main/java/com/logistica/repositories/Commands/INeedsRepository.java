package com.logistica.repositories.Commands;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Commands.Needs;
import org.springframework.stereotype.Repository;

@Repository
public interface INeedsRepository extends IBaseJpaRepository<Needs> {
}
