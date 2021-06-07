package com.logistica.services.Products.OutputDetail;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.OutputDetails;
import com.logistica.dtos.Products.TransactionDetail.OutputDetailCreateDto;
import com.logistica.dtos.Products.TransactionDetail.OutputDetailDto;
import com.logistica.dtos.Products.TransactionDetail.OutputDetailUpdateDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IOutputDetailService extends IBaseCrudService<OutputDetails, OutputDetailDto, OutputDetailCreateDto, OutputDetailUpdateDto> {
    CompletableFuture<List<OutputDetailDto>> getByOutputId(Long id);
}
