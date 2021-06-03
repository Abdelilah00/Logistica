package com.logistica.services.Products.TransferDetails;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.TransferDetails;
import com.logistica.dtos.Products.TransferDetails.TransferDetailsCreateDto;
import com.logistica.dtos.Products.TransferDetails.TransferDetailsDto;
import com.logistica.dtos.Products.TransferDetails.TransferDetailsUpdateDto;
import org.springframework.stereotype.Service;

public interface ITransferDetailsService extends IBaseCrudService<TransferDetails, TransferDetailsDto, TransferDetailsCreateDto, TransferDetailsUpdateDto> {

}
