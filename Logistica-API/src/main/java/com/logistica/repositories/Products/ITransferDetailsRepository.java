package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Products.TransferDetails;

@Repository
public interface ITransferDetailsRepository extends IBaseJpaRepository<TransferDetails> {
}
