package com.logistica.services.Products.TransactionDetail;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.TransactionDetail;
import com.logistica.dtos.Products.TransactionDetail.TransactionDetailCreateDto;
import com.logistica.dtos.Products.TransactionDetail.TransactionDetailDto;
import com.logistica.dtos.Products.TransactionDetail.TransactionDetailUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ITransactionDetailService extends IBaseCrudService<TransactionDetail, TransactionDetailDto, TransactionDetailCreateDto, TransactionDetailUpdateDto> {
    CompletableFuture<List<TransactionDetailDto>> getByInputId(Long id);
}
