package com.logistica.services.Products.InputDetail;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.InputDetails;
import com.logistica.dtos.Products.TransactionDetail.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IInputDetailService extends IBaseCrudService<InputDetails, InputDetailDto, InputDetailCreateDto, InputDetailUpdateDto> {
    CompletableFuture<List<InputDetailDto>> getByInputId(Long id);

}
