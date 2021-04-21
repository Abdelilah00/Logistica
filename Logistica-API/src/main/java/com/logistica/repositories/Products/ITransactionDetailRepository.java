package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Products.TransactionDetail;

@Repository
public interface ITransactionDetailRepository extends IBaseJpaRepository<TransactionDetail> {
}
