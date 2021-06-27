package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Products.Transfer;

@Repository
public interface ITransferRepository extends IBaseJpaRepository<Transfer> {
}
