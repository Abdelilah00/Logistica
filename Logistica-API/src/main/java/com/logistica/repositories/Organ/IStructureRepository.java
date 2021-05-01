package com.logistica.repositories.Organ;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Organ.Structure;
import org.springframework.stereotype.Repository;

@Repository
public interface IStructureRepository extends IBaseJpaRepository<Structure> {
}
