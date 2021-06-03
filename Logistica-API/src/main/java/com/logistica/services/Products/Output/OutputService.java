package com.logistica.services.Products.Output;

import com.alexy.services.BaseCrudServiceImpl;
import com.configuration.Exception.UserFriendlyException;
import com.logistica.domains.Products.Output;
import com.logistica.domains.Products.TransactionDetail;
import com.logistica.dtos.Products.Output.OutputCreateDto;
import com.logistica.dtos.Products.Output.OutputDto;
import com.logistica.dtos.Products.Output.OutputUpdateDto;
import com.logistica.repositories.Products.IStockProductRepository;
import com.logistica.repositories.Products.IStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class OutputService extends BaseCrudServiceImpl<Output, OutputDto, OutputCreateDto, OutputUpdateDto> implements IOutputService {

    public OutputService() {
        super(Output.class, OutputDto.class, OutputCreateDto.class, OutputUpdateDto.class);
    }

    @Autowired
    private IStockProductRepository iStockProductRepository;
    @Autowired
    private IStockRepository iStockRepository;

    @Override
    public CompletableFuture<OutputDto> create(OutputCreateDto outputCreateDto) throws UserFriendlyException {
        var output = objectMapper.convertToEntity(outputCreateDto);

        output.setTransactionDetails(outputCreateDto.getTransactionDetails()
                .stream().map(transactionDetailCreateDto -> {
                    var transaction = new TransactionDetail();

                    //create new product
                    transaction.getProduct().setId(transactionDetailCreateDto.getProductId());
                    transaction.setLot(transactionDetailCreateDto.getLot());
                    transaction.setArticle(transactionDetailCreateDto.getArticle());
                    transaction.setPriceHT(transactionDetailCreateDto.getPriceHT());

                    transaction.setQte(transactionDetailCreateDto.getQte());

                    //insert qte to stockproduct principale - increment if prod exist in stock else create new one
                    var defaultStockProd = iStockProductRepository.findByProductIdAndStockId(transactionDetailCreateDto.getProductId(), transactionDetailCreateDto.getStockId());

                    if (defaultStockProd.getQte() > transactionDetailCreateDto.getQte()) {
                        defaultStockProd.setQte(defaultStockProd.getQte() - transactionDetailCreateDto.getQte());
                    } //else throw new UserFriendlyException("the qte not available in this stock try to split your needs on many stocks");


                    iStockProductRepository.save(defaultStockProd);

                    transaction.setOutput(output);
                    return transaction;
                }).collect(Collectors.toList()));

        return CompletableFuture.completedFuture(objectMapper.convertToDto(repository.save(output), OutputDto.class));
    }
}
