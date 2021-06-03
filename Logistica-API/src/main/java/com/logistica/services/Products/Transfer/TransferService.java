package com.logistica.services.Products.Transfer;

import com.alexy.services.BaseCrudServiceImpl;
import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.Transfer;
import com.logistica.dtos.Products.Transfer.TransferCreateDto;
import com.logistica.dtos.Products.Transfer.TransferDto;
import com.logistica.dtos.Products.Transfer.TransferUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class TransferService extends BaseCrudServiceImpl<Transfer, TransferDto, TransferCreateDto, TransferUpdateDto> implements ITransferService {

    public TransferService() {
        super(Transfer.class, TransferDto.class, TransferCreateDto.class, TransferUpdateDto.class);
    }
}
