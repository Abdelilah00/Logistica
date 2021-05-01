package com.logistica.repositories.Commands;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Commands.Sector;
import org.springframework.stereotype.Repository;

@Repository
public interface ISectorRepository extends IBaseJpaRepository<Sector> {
}
