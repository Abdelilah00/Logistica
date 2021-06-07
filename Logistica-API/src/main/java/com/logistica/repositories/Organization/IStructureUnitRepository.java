package com.logistica.repositories.Organization;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Organization.StructureUnit;
import org.springframework.stereotype.Repository;

@Repository
public interface IStructureUnitRepository extends IBaseJpaRepository<StructureUnit> {
}
