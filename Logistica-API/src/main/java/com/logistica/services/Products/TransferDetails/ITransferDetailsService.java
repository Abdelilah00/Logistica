package com.logistica.services.Products.TransferDetails;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.TransferDetails;
import com.logistica.dtos.Products.TransferDetails.TransferDetailsCreateDto;
import com.logistica.dtos.Products.TransferDetails.TransferDetailsDto;
import com.logistica.dtos.Products.TransferDetails.TransferDetailsUpdateDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ITransferDetailsService extends IBaseCrudService<TransferDetails, TransferDetailsDto, TransferDetailsCreateDto, TransferDetailsUpdateDto> {
    CompletableFuture<List<TransferDetailsDto>> getByTransferId(Long id);


}
