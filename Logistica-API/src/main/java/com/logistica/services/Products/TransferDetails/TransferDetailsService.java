package com.logistica.services.Products.TransferDetails;

import com.alexy.services.BaseCrudServiceImpl;
import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.TransferDetails;
import com.logistica.dtos.Products.TransferDetails.TransferDetailsCreateDto;
import com.logistica.dtos.Products.TransferDetails.TransferDetailsDto;
import com.logistica.dtos.Products.TransferDetails.TransferDetailsUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class TransferDetailsService extends BaseCrudServiceImpl<TransferDetails, TransferDetailsDto, TransferDetailsCreateDto, TransferDetailsUpdateDto> implements ITransferDetailsService {

    public TransferDetailsService() {
        super(TransferDetails.class, TransferDetailsDto.class, TransferDetailsCreateDto.class, TransferDetailsUpdateDto.class);
    }
}
