package com.logistica.repositories.Organization;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Organization.Structure;
import org.springframework.stereotype.Repository;

@Repository
public interface IStructureRepository extends IBaseJpaRepository<Structure> {
}
