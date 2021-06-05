package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Products.TransferDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITransferDetailsRepository extends IBaseJpaRepository<TransferDetails> {
    List<TransferDetails> getByTransferId(Long id);
}
