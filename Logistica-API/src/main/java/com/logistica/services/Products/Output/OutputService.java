package com.logistica.services.Products.Output;

import com.alexy.services.BaseCrudServiceImpl;
import com.configuration.Exception.UserFriendlyException;
import com.logistica.domains.Products.Output;
import com.logistica.domains.Products.StockProduct;
import com.logistica.domains.Products.TransactionDetail;
import com.logistica.dtos.Products.Output.OutputCreateDto;
import com.logistica.dtos.Products.Output.OutputDto;
import com.logistica.dtos.Products.Output.OutputUpdateDto;
import com.logistica.repositories.Products.IStockProductRepository;
import com.logistica.repositories.Products.IStockRepository;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
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

    @PersistenceContext
    EntityManager entityManager;


    @Override
    //todo eliminate all combination of prodId/StockId
    public CompletableFuture<OutputDto> create(OutputCreateDto outputCreateDto) throws UserFriendlyException {
        var output = objectMapper.convertToEntity(outputCreateDto);
        Session session = entityManager.unwrap(Session.class);
        session.setFlushMode(FlushMode.COMMIT);


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
            var stockProd = iStockProductRepository.findByProductIdAndStockId(transactionDto.getProductId(), transactionDto.getStockId());

            if (stockProd == null)
                throw new UserFriendlyException("product does't exist in this stock");
            if (stockProd.getQte() >= transactionDto.getQte()) {
                stockProd.setQte(stockProd.getQte() - transactionDto.getQte());
            } else {
                session.clear();
                throw new UserFriendlyException("the qte not available in this stock try to split your needs on many stocks");
            }
            transaction.setOutput(output);
            output.getTransactionDetails().add(transaction);
        }

        return CompletableFuture.completedFuture(objectMapper.convertToDto(repository.save(output), OutputDto.class));
    }
}
