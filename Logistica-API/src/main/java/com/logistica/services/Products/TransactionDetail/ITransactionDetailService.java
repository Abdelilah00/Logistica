package com.logistica.services.Products.TransactionDetail;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.TransactionDetail;
import com.logistica.dtos.Products.TransactionDetail.TransactionDetailCreateDto;
import com.logistica.dtos.Products.TransactionDetail.TransactionDetailDto;
import com.logistica.dtos.Products.TransactionDetail.TransactionDetailUpdateDto;
import org.springframework.stereotype.Service;

public interface ITransactionDetailService extends IBaseCrudService<TransactionDetail, TransactionDetailDto, TransactionDetailCreateDto, TransactionDetailUpdateDto> {

}