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

        for (int i = 0; i < outputCreateDto.getTransactionDetails().size(); i++) {
            var transactionDto = outputCreateDto.getTransactionDetails().get(i);
            var transaction = new TransactionDetail();
            ///map attributes from dto to entity
            transaction.getProduct().setId(transactionDto.getProductId());
            transaction.setLot(transactionDto.getLot());
            transaction.setArticle(transactionDto.getArticle());
            transaction.setPriceHT(transactionDto.getPriceHT());
            transaction.setQte(transactionDto.getQte());

            //insert qte to stockproduct principale - increment if prod exist in stock else create new one
            var defaultStockProd = iStockProductRepository.findByProductIdAndStockId(transactionDto.getProductId(), transactionDto.getStockId());

            if (defaultStockProd == null)
                throw new UserFriendlyException("product does't exist in this stock");
            if (defaultStockProd.getQte() > transactionDto.getQte()) {
                defaultStockProd.setQte(defaultStockProd.getQte() - transactionDto.getQte());
            } else
                throw new UserFriendlyException("the qte not available in this stock try to split your needs on many stocks");
            iStockProductRepository.save(defaultStockProd);

            transaction.setOutput(output);
            output.getTransactionDetails().add(transaction);
        }

        return CompletableFuture.completedFuture(objectMapper.convertToDto(repository.save(output), OutputDto.class));
    }
}
