package com.logistica.controllers.Products;

import com.alexy.controllers.BaseCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.logistica.domains.Products.TransferDetails;
import com.logistica.dtos.Products.TransferDetails.TransferDetailsCreateDto;
import com.logistica.dtos.Products.TransferDetails.TransferDetailsDto;
import com.logistica.dtos.Products.TransferDetails.TransferDetailsUpdateDto;

@RestController
@RequestMapping("api/transferdetailss")
public class TransferDetailsController extends BaseCrudController<TransferDetails, TransferDetailsDto, TransferDetailsCreateDto, TransferDetailsUpdateDto> {
}
