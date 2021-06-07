package com.logistica.controllers.Products;

import com.alexy.controllers.BaseCrudController;
import com.logistica.domains.Products.InputDetails;
import com.logistica.dtos.Products.TransactionDetail.InputDetailCreateDto;
import com.logistica.dtos.Products.TransactionDetail.InputDetailDto;
import com.logistica.dtos.Products.TransactionDetail.InputDetailUpdateDto;
import com.logistica.services.Products.InputDetail.IInputDetailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/inputDetails")
public class InputDetailController extends BaseCrudController<InputDetails, InputDetailDto, InputDetailCreateDto, InputDetailUpdateDto> {

    @GetMapping(path = "/getByInputId/{id}")
    protected List<InputDetailDto> getByInputId(@PathVariable(value = "id") Long id) throws ExecutionException, InterruptedException {
        return ((IInputDetailService) service).getByInputId(id).get();
    }
}
