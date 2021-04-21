package com.logistica.repositories.Commands;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Commands.Sector;

@Repository
public interface ISectorRepository extends IBaseJpaRepository<Sector> {
}
