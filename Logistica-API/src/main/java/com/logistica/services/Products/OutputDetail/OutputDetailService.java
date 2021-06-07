package com.logistica.services.Products.OutputDetail;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Products.OutputDetails;
import com.logistica.dtos.Products.TransactionDetail.InputDetailDto;
import com.logistica.dtos.Products.TransactionDetail.OutputDetailCreateDto;
import com.logistica.dtos.Products.TransactionDetail.OutputDetailDto;
import com.logistica.dtos.Products.TransactionDetail.OutputDetailUpdateDto;
import com.logistica.repositories.Products.IOutputDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class OutputDetailService extends BaseCrudServiceImpl<OutputDetails, OutputDetailDto, OutputDetailCreateDto, OutputDetailUpdateDto> implements IOutputDetailService {

    public OutputDetailService() {
        super(OutputDetails.class, OutputDetailDto.class, OutputDetailCreateDto.class, OutputDetailUpdateDto.class);
    }

    public CompletableFuture<List<OutputDetailDto>> getByOutputId(Long id) {
        var tmp = ((IOutputDetailRepository) repository).getByOutputId(id);
        return CompletableFuture.completedFuture(objectMapper.convertToDtoList(tmp, InputDetailDto.class));
    }
}
