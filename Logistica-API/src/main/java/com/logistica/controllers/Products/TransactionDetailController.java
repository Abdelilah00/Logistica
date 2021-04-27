package com.logistica.controllers.Products;

import com.alexy.controllers.BaseCrudController;
import com.logistica.domains.Products.TransactionDetail;
import com.logistica.dtos.Products.TransactionDetail.TransactionDetailCreateDto;
import com.logistica.dtos.Products.TransactionDetail.TransactionDetailDto;
import com.logistica.dtos.Products.TransactionDetail.TransactionDetailUpdateDto;
import com.logistica.services.Products.TransactionDetail.ITransactionDetailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/transactionDetails")
public class TransactionDetailController extends BaseCrudController<TransactionDetail, TransactionDetailDto, TransactionDetailCreateDto, TransactionDetailUpdateDto> {

    @GetMapping(path = "/getByInputId/{inputId}")
    protected List<TransactionDetailDto> getByInputId(@PathVariable(value = "inputId") Long id) throws ExecutionException, InterruptedException {
        return ((ITransactionDetailService) service).getByInputId(id).get();
    }
}
