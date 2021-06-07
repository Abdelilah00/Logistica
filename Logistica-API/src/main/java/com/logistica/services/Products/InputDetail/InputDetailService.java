package com.logistica.services.Products.InputDetail;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Products.InputDetails;
import com.logistica.dtos.Products.TransactionDetail.InputDetailCreateDto;
import com.logistica.dtos.Products.TransactionDetail.InputDetailDto;
import com.logistica.dtos.Products.TransactionDetail.InputDetailUpdateDto;
import com.logistica.repositories.Products.IInputDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class InputDetailService extends BaseCrudServiceImpl<InputDetails, InputDetailDto, InputDetailCreateDto, InputDetailUpdateDto> implements IInputDetailService {

    public InputDetailService() {
        super(InputDetails.class, InputDetailDto.class, InputDetailCreateDto.class, InputDetailUpdateDto.class);
    }

    public CompletableFuture<List<InputDetailDto>> getByInputId(Long id) {
        var tmp = ((IInputDetailRepository) repository).getByInputId(id);
        return CompletableFuture.completedFuture(objectMapper.convertToDtoList(tmp, InputDetailDto.class));
    }
}

