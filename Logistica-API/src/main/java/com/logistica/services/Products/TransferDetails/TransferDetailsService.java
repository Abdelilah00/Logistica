package com.logistica.services.Products.TransferDetails;

import com.alexy.services.BaseCrudServiceImpl;
import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.TransferDetails;
import com.logistica.dtos.Products.TransactionDetail.TransactionDetailDto;
import com.logistica.dtos.Products.TransferDetails.TransferDetailsCreateDto;
import com.logistica.dtos.Products.TransferDetails.TransferDetailsDto;
import com.logistica.dtos.Products.TransferDetails.TransferDetailsUpdateDto;
import com.logistica.repositories.Products.ITransactionDetailRepository;
import com.logistica.repositories.Products.ITransferDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TransferDetailsService extends BaseCrudServiceImpl<TransferDetails, TransferDetailsDto, TransferDetailsCreateDto, TransferDetailsUpdateDto> implements ITransferDetailsService {

    public TransferDetailsService() {
        super(TransferDetails.class, TransferDetailsDto.class, TransferDetailsCreateDto.class, TransferDetailsUpdateDto.class);
    }

    public CompletableFuture<List<TransferDetailsDto>> getByTransferId(Long id) {
        var tmp = ((ITransferDetailsRepository) repository).getByTransferId(id);
        return CompletableFuture.completedFuture(objectMapper.convertToDtoList(tmp, TransferDetailsDto.class));
    }
}
