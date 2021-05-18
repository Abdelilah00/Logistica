package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Products.TransactionDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITransactionDetailRepository extends IBaseJpaRepository<TransactionDetail> {
    List<TransactionDetail> getByInputId(Long id);

    List<TransactionDetail> getByOutputId(Long id);
}
