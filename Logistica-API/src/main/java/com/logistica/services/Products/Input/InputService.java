package com.logistica.services.Products.Input;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Products.Input;
import com.logistica.domains.Products.StockProduct;
import com.logistica.domains.Products.TransactionDetail;
import com.logistica.dtos.Products.Input.InputCreateDto;
import com.logistica.dtos.Products.Input.InputDto;
import com.logistica.dtos.Products.Input.InputUpdateDto;
import com.logistica.repositories.Products.IStockProductRepository;
import com.logistica.repositories.Products.IStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class InputService extends BaseCrudServiceImpl<Input, InputDto, InputCreateDto, InputUpdateDto> implements IInputService {

    public InputService() {
        super(Input.class, InputDto.class, InputCreateDto.class, InputUpdateDto.class);
    }

    @Autowired
    private IStockProductRepository iStockProductRepository;
    @Autowired
    private IStockRepository iStockRepository;


    //todo: create product if not exist
    @Override
    public CompletableFuture<InputDto> create(InputCreateDto inputCreateDto) {
        //isAuth
        var input = objectMapper.convertToEntity(inputCreateDto);

        //var objectMapperTransactionDetail = new ModelEntityMapping<TransactionDetail>();

        for (int i = 0; i < inputCreateDto.getTransactionDetails().size(); i++) {
            var transactionDto = inputCreateDto.getTransactionDetails().get(i);

            var transaction = new TransactionDetail();

            //create new product
            transaction.getProduct().setId(transactionDto.getProductId());
            transaction.setLot(transactionDto.getLot());
            transaction.setArticle(transactionDto.getArticle());
            transaction.setExpDate(transactionDto.getExpDate());
            transaction.setPriceHT(transactionDto.getPriceHT());

            //set default values from settings(front end)
            transaction.getProduct().setStockMin(100);
            transaction.getProduct().setStockMax(1000);
            transaction.getProduct().setStockSecurity(350);
            transaction.setQte(transactionDto.getQte());

            //insert qte to stockproduct principale - increment if prod exist in stock else create new one
            var defaultStockProd = iStockProductRepository.findByProductIdAndStockDefIsTrue(transactionDto.getProductId());
            if (defaultStockProd == null) {
                //insert new
                defaultStockProd = new StockProduct();
                defaultStockProd.getProduct().setId(transactionDto.getProductId());
                defaultStockProd.getStock().setId(iStockRepository.getStockByDefIsTrue().getId());
                defaultStockProd.setQte(transactionDto.getQte());
            } else {
                //update old
                defaultStockProd.setQte(defaultStockProd.getQte() + transactionDto.getQte());
            }
            iStockProductRepository.save(defaultStockProd);
            transaction.setInput(input);
        }

        return CompletableFuture.completedFuture(objectMapper.convertToDto(repository.save(input), InputDto.class));
    }

}
