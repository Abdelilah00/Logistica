package com.logistica.repositories;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Test;
import org.springframework.stereotype.Repository;

@Repository
public interface ITestRepository extends IBaseJpaRepository<Test> {
}
