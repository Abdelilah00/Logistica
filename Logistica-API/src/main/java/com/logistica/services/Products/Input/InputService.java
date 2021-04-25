package com.logistica.services.Products.Input;

import com.alexy.services.BaseCrudServiceImpl;
import com.configuration.Exception.UserFriendlyException;
import com.logistica.domains.Products.Input;
import com.logistica.domains.Products.Product;
import com.logistica.domains.Products.TransactionDetail;
import com.logistica.dtos.Products.Input.InputCreateDto;
import com.logistica.dtos.Products.Input.InputDto;
import com.logistica.dtos.Products.Input.InputUpdateDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class InputService extends BaseCrudServiceImpl<Input, InputDto, InputCreateDto, InputUpdateDto> implements IInputService {

    public InputService() {
        super(Input.class, InputDto.class, InputCreateDto.class, InputUpdateDto.class);
    }

    //todo: create product if not exist
    @Override
    public CompletableFuture<InputDto> create(InputCreateDto inputCreateDto)   {

        var input = objectMapper.convertToEntity(inputCreateDto);

        input.setTransactionDetails(inputCreateDto.getTransactionDetailCreateDtos().stream().map(transactionDetailCreateDto -> {
            var transaction = new TransactionDetail();

            transaction.getProduct().setName(transactionDetailCreateDto.getProductCreateDto().getName());
            transaction.getProduct().getCategory().setId(transactionDetailCreateDto.getProductCreateDto().getId());
            transaction.getProduct().setPriceHT(transactionDetailCreateDto.getProductCreateDto().getPriceHT());
            transaction.getProduct().setExpDate(transactionDetailCreateDto.getProductCreateDto().getExpDate());
            transaction.getProduct().setStockMin(transactionDetailCreateDto.getProductCreateDto().getStockMin());
            transaction.getProduct().setStockMax(transactionDetailCreateDto.getProductCreateDto().getStockMax());
            transaction.getProduct().setStockSecurity(transactionDetailCreateDto.getProductCreateDto().getStockSecurity());

            transaction.setLot(transactionDetailCreateDto.getLot());
            transaction.setArticle(transactionDetailCreateDto.getArticle());
            transaction.setQte(transactionDetailCreateDto.getQte());

            transaction.setInput(input);
            return transaction;
        }).collect(Collectors.toList()));

        return CompletableFuture.completedFuture(objectMapper.convertToDto(repository.save(input), InputDto.class));
    }
}
