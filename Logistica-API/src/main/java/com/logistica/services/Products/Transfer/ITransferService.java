package com.logistica.services.Products.Transfer;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.Transfer;
import com.logistica.dtos.Products.Transfer.TransferCreateDto;
import com.logistica.dtos.Products.Transfer.TransferDto;
import com.logistica.dtos.Products.Transfer.TransferUpdateDto;
import org.springframework.stereotype.Service;

public interface ITransferService extends IBaseCrudService<Transfer, TransferDto, TransferCreateDto, TransferUpdateDto> {

}
