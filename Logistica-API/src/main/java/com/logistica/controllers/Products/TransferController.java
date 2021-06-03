package com.logistica.controllers.Products;

import com.alexy.controllers.BaseCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.logistica.domains.Products.Transfer;
import com.logistica.dtos.Products.Transfer.TransferCreateDto;
import com.logistica.dtos.Products.Transfer.TransferDto;
import com.logistica.dtos.Products.Transfer.TransferUpdateDto;

@RestController
@RequestMapping("api/transfers")
public class TransferController extends BaseCrudController<Transfer, TransferDto, TransferCreateDto, TransferUpdateDto> {
}
