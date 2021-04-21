package com.logistica.repositories.Organ;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Organ.Structure;

@Repository
public interface IStructureRepository extends IBaseJpaRepository<Structure> {
}
