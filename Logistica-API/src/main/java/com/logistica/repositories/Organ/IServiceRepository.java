package com.logistica.repositories.Organ;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Organ.Service;

@Repository
public interface IServiceRepository extends IBaseJpaRepository<Service> {
}
