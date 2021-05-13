package com.logistica.services.Products.Output;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Products.Output;
import com.logistica.domains.Products.TransactionDetail;
import com.logistica.dtos.Products.Input.InputDto;
import com.logistica.dtos.Products.Output.OutputCreateDto;
import com.logistica.dtos.Products.Output.OutputDto;
import com.logistica.dtos.Products.Output.OutputUpdateDto;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class OutputService extends BaseCrudServiceImpl<Output, OutputDto, OutputCreateDto, OutputUpdateDto> implements IOutputService {

    public OutputService() {
        super(Output.class, OutputDto.class, OutputCreateDto.class, OutputUpdateDto.class);
    }

    //todo: create product if not exist
    @Override
    public CompletableFuture<OutputDto> create(OutputCreateDto outputCreateDto) {

        // isAuth
        var output = objectMapper.convertToEntity(outputCreateDto);

        output.setTransactionDetails(outputCreateDto.getTransactionDetails().stream().map(transactionDetailCreateDto -> {
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

            transaction.setOutput(output);
            return transaction;
        }).collect(Collectors.toList()));

        return CompletableFuture.completedFuture(objectMapper.convertToDto(repository.save(output), InputDto.class));
    }
}
