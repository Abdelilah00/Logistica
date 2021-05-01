package com.logistica.repositories.Organ;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Organ.Service;
import org.springframework.stereotype.Repository;

@Repository
public interface IServiceRepository extends IBaseJpaRepository<Service> {
}
