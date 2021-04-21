package com.logistica.services.Products.TransactionDetail;

import com.alexy.services.BaseCrudServiceImpl;
import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.TransactionDetail;
import com.logistica.dtos.Products.TransactionDetail.TransactionDetailCreateDto;
import com.logistica.dtos.Products.TransactionDetail.TransactionDetailDto;
import com.logistica.dtos.Products.TransactionDetail.TransactionDetailUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class TransactionDetailService extends BaseCrudServiceImpl<TransactionDetail, TransactionDetailDto, TransactionDetailCreateDto, TransactionDetailUpdateDto> implements ITransactionDetailService {

    public TransactionDetailService() {
        super(TransactionDetail.class, TransactionDetailDto.class, TransactionDetailCreateDto.class, TransactionDetailUpdateDto.class);
    }
}
