package com.logistica.services.Products.Input;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Products.Input;
import com.logistica.domains.Products.TransactionDetail;
import com.logistica.dtos.Products.Input.InputCreateDto;
import com.logistica.dtos.Products.Input.InputDto;
import com.logistica.dtos.Products.Input.InputUpdateDto;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class InputService extends BaseCrudServiceImpl<Input, InputDto, InputCreateDto, InputUpdateDto> implements IInputService {

    public InputService() {
        super(Input.class, InputDto.class, InputCreateDto.class, InputUpdateDto.class);
    }

    //todo: create product if not exist
    @Override
    public CompletableFuture<InputDto> create(InputCreateDto inputCreateDto) {
        //isAuth
        var input = objectMapper.convertToEntity(inputCreateDto);

        input.setTransactionDetails(inputCreateDto.getTransactionDetails().stream().map(transactionDetailCreateDto -> {
            var transaction = new TransactionDetail();

            if (transactionDetailCreateDto.getProductId() != null)
                //use existing product
                transaction.getProduct().setId(transactionDetailCreateDto.getProductId());
            else {
                //create new product
                transaction.getProduct().setName(transactionDetailCreateDto.getProductName());
                transaction.getProduct().getCategory().setId(1);

                //set default values from settings(front end)
                transaction.getProduct().setStockMin(100);
                transaction.getProduct().setStockMax(1000);
                transaction.getProduct().setStockSecurity(350);
            }

            transaction.setLot(transactionDetailCreateDto.getLot());
            transaction.setArticle(transactionDetailCreateDto.getArticle());
            transaction.setQte(transactionDetailCreateDto.getQte());
            transaction.setExpDate(transactionDetailCreateDto.getExpDate());
            transaction.setPriceHT(transactionDetailCreateDto.getPriceHT());

            transaction.setInput(input);
            return transaction;
        }).collect(Collectors.toList()));

        return CompletableFuture.completedFuture(objectMapper.convertToDto(repository.save(input), InputDto.class));
    }

}
