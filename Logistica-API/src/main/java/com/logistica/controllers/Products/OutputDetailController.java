package com.logistica.controllers.Products;

import com.alexy.controllers.BaseCrudController;
import com.logistica.domains.Products.OutputDetails;
import com.logistica.dtos.Products.TransactionDetail.*;
import com.logistica.services.Products.OutputDetail.IOutputDetailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/outputDetails")
public class OutputDetailController extends BaseCrudController<OutputDetails, OutputDetailDto, OutputDetailCreateDto, OutputDetailUpdateDto> {

    @GetMapping(path = "/getByOutputId/{id}")
    protected List<OutputDetailDto> getByOutputId(@PathVariable(value = "id") Long id) throws ExecutionException, InterruptedException {
        return ((IOutputDetailService) service).getByOutputId(id).get();
    }
}

