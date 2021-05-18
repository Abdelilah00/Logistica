package com.logistica.services.Products.TransactionDetail;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Products.TransactionDetail;
import com.logistica.dtos.Products.TransactionDetail.TransactionDetailCreateDto;
import com.logistica.dtos.Products.TransactionDetail.TransactionDetailDto;
import com.logistica.dtos.Products.TransactionDetail.TransactionDetailUpdateDto;
import com.logistica.repositories.Products.ITransactionDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TransactionDetailService extends BaseCrudServiceImpl<TransactionDetail, TransactionDetailDto, TransactionDetailCreateDto, TransactionDetailUpdateDto> implements ITransactionDetailService {

    public TransactionDetailService() {
        super(TransactionDetail.class, TransactionDetailDto.class, TransactionDetailCreateDto.class, TransactionDetailUpdateDto.class);
    }

    public CompletableFuture<List<TransactionDetailDto>> getByInputId(Long id) {
        var tmp = ((ITransactionDetailRepository) repository).getByInputId(id);
        return CompletableFuture.completedFuture(objectMapper.convertToDtoList(tmp, TransactionDetailDto.class));
    }

    public CompletableFuture<List<TransactionDetailDto>> getByOutputId(Long id) {
        var tmp = ((ITransactionDetailRepository) repository).getByOutputId(id);
        return CompletableFuture.completedFuture(objectMapper.convertToDtoList(tmp, TransactionDetailDto.class));
    }

}
