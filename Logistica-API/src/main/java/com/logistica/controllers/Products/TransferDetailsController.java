package com.logistica.controllers.Products;

import com.alexy.controllers.BaseCrudController;
import com.logistica.domains.Products.TransferDetails;
import com.logistica.dtos.Products.TransferDetails.TransferDetailsCreateDto;
import com.logistica.dtos.Products.TransferDetails.TransferDetailsDto;
import com.logistica.dtos.Products.TransferDetails.TransferDetailsUpdateDto;
import com.logistica.services.Products.TransferDetails.ITransferDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/transferDetails")
public class TransferDetailsController extends BaseCrudController<TransferDetails, TransferDetailsDto, TransferDetailsCreateDto, TransferDetailsUpdateDto> {
    //getByTransferId
    @GetMapping(path = "/getByTransferId/{id}")
    protected List<TransferDetailsDto> getByTransferId(@PathVariable(value = "id") Long id) throws ExecutionException, InterruptedException {
        return ((ITransferDetailsService) service).getByTransferId(id).get();
    }
}
