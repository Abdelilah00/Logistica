package com.logistica.services.Products.Output;

import com.alexy.services.BaseCrudServiceImpl;
import com.configuration.Exception.UserFriendlyException;
import com.logistica.domains.Products.Output;
import com.logistica.domains.Products.OutputDetails;
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


        for (int i = 0; i < outputCreateDto.getOutputDetails().size(); i++) {
            var transactionDto = outputCreateDto.getOutputDetails().get(i);
            var outputDetails = new OutputDetails();
            ///map attributes from dto to entity
            outputDetails.getProduct().setId(transactionDto.getProductId());
            outputDetails.setLot(transactionDto.getLot());
            outputDetails.setArticle(transactionDto.getArticle());
            outputDetails.setPriceHT(transactionDto.getPriceHT());
            outputDetails.setQte(transactionDto.getQte());

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
            outputDetails.setOutput(output);
            output.getOutputDetails().add(outputDetails);
        }

        return CompletableFuture.completedFuture(objectMapper.convertToDto(repository.save(output), OutputDto.class));
    }
}
