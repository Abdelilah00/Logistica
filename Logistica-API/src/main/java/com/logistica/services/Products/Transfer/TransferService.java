package com.logistica.services.Products.Transfer;

import com.alexy.services.BaseCrudServiceImpl;
import com.configuration.Exception.UserFriendlyException;
import com.logistica.domains.Products.Transfer;
import com.logistica.dtos.Products.Transfer.TransferCreateDto;
import com.logistica.dtos.Products.Transfer.TransferDto;
import com.logistica.dtos.Products.Transfer.TransferUpdateDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TransferService extends BaseCrudServiceImpl<Transfer, TransferDto, TransferCreateDto, TransferUpdateDto> implements ITransferService {

    public TransferService() {
        super(Transfer.class, TransferDto.class, TransferCreateDto.class, TransferUpdateDto.class);
    }

    @Override
    public CompletableFuture<TransferDto> create(TransferCreateDto transferCreateDto) throws UserFriendlyException {
        //todo sub fromStock and add to toStock
        //todo sub from service and add to service
        //todo verify if productQte available fromStock
        var transfer = objectMapper.convertToEntity(transferCreateDto);


        return CompletableFuture.completedFuture(objectMapper.convertToDto(repository.save(transfer), TransferDto.class));
    }

    @Override
    public CompletableFuture<List<TransferDto>> findAll(Pageable pageable) {
        var x = repository.findAll(pageable).toList();
        List<TransferDto> y = objectMapper.convertToDtoList(x, TransferDto.class);
        return CompletableFuture.completedFuture(y);
    }
}
