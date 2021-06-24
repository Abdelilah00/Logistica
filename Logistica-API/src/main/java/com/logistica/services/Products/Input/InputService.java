package com.logistica.services.Products.Input;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Products.Input;
import com.logistica.domains.Products.InputDetails;
import com.logistica.domains.Products.StockProduct;
import com.logistica.dtos.Products.Input.InputCreateDto;
import com.logistica.dtos.Products.Input.InputDto;
import com.logistica.dtos.Products.Input.InputUpdateDto;
import com.logistica.repositories.Products.IStockProductRepository;
import com.logistica.repositories.Products.IStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class InputService extends BaseCrudServiceImpl<Input, InputDto, InputCreateDto, InputUpdateDto> implements IInputService {

    public InputService() {
        super(Input.class, InputDto.class, InputCreateDto.class, InputUpdateDto.class);
    }

    @Autowired
    private IStockProductRepository iStockProductRepository;
    @Autowired
    private IStockRepository iStockRepository;
    private final List<StockProduct> stockProducts = new ArrayList<>();

    //todo: create product if not exist
    @Override
    public CompletableFuture<InputDto> create(InputCreateDto inputCreateDto) {
        //isAuth
        var input = objectMapper.convertToEntity(inputCreateDto);

        //var objectMapperTransactionDetail = new ModelEntityMapping<TransactionDetail>();

        for (int i = 0; i < inputCreateDto.getInputDetails().size(); i++) {
            var transactionDto = inputCreateDto.getInputDetails().get(i);

            //var inputDetails = new InputDetails();

            //create new product
            //inputDetails.getProduct().setId(transactionDto.getProductId());
            //inputDetails.setLot(transactionDto.getLot());
            //inputDetails.setArticle(transactionDto.getArticle());
            //inputDetails.setExpDate(transactionDto.getExpDate());

            //set default values from settings(front end)
            //inputDetails.getProduct().setStockMin(100);
            //inputDetails.getProduct().setStockMax(1000);
            //inputDetails.getProduct().setStockSecurity(350);
            //inputDetails.setQte(transactionDto.getQte());

            //insert qte to stockproduct principale - increment if prod exist in stock else create new one
            var stockProd = iStockProductRepository.findByProductIdAndStockId(transactionDto.getProductId(), transactionDto.getStockId());
            if (stockProd == null) {
                //insert new
                //todo sum of group by prod-stock
                stockProd = new StockProduct();
                stockProd.getProduct().setId(transactionDto.getProductId());
                stockProd.getStock().setId(transactionDto.getStockId());
                stockProd.setQte(transactionDto.getQte());
            } else {
                //update old
                stockProd.setQte(stockProd.getQte() + transactionDto.getQte());
            }
            stockProducts.add(stockProd);
            input.getInputDetails().get(i).setInput(input);
        }

        iStockProductRepository.saveAll(stockProducts);
        return CompletableFuture.completedFuture(objectMapper.convertToDto(repository.save(input), InputDto.class));
    }
}
