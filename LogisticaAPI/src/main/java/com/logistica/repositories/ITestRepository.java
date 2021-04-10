package com.logistica.repositories;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Test;

@Repository
public interface ITestRepository extends IBaseJpaRepository<Test> {
}
