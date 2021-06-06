package com.logistica.services.Products.Transfer;

import com.alexy.services.BaseCrudServiceImpl;
import com.configuration.Exception.UserFriendlyException;
import com.logistica.domains.Products.StockProduct;
import com.logistica.domains.Products.Transfer;
import com.logistica.dtos.Products.Transfer.TransferCreateDto;
import com.logistica.dtos.Products.Transfer.TransferDto;
import com.logistica.dtos.Products.Transfer.TransferUpdateDto;
import com.logistica.repositories.Products.IStockProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TransferService extends BaseCrudServiceImpl<Transfer, TransferDto, TransferCreateDto, TransferUpdateDto> implements ITransferService {
    @Autowired
    private IStockProductRepository iStockProductRepository;

    public TransferService() {
        super(Transfer.class, TransferDto.class, TransferCreateDto.class, TransferUpdateDto.class);
    }

    @Override
    public CompletableFuture<TransferDto> create(TransferCreateDto transferCreateDto) throws UserFriendlyException {
        //todo sub from service and add to service
        var transfer = objectMapper.convertToEntity(transferCreateDto);
        if (transfer.getIsStocksTransfer()) {
            for (int i = 0; i < transfer.getTransferDetails().size(); i++) {
                var transferDetails = transfer.getTransferDetails().get(i);

                var fromStockProd = iStockProductRepository.findByProductIdAndStockId(transferDetails.getProduct().getId(), transfer.getFromStock().getId());
                if (fromStockProd == null)
                    throw new UserFriendlyException("Product does't exist in the stock");
                if (transferDetails.getQte() > fromStockProd.getQte())
                    throw new UserFriendlyException("Qte does't available in the stock");
                fromStockProd.setQte(fromStockProd.getQte() - transferDetails.getQte());

                var toStockProd = iStockProductRepository.findByProductIdAndStockId(transferDetails.getProduct().getId(), transfer.getToStock().getId());
                if (toStockProd == null) {
                    toStockProd = new StockProduct();
                    toStockProd.setQte(transferDetails.getQte());
                    toStockProd.getProduct().setId(transferDetails.getId());
                    toStockProd.getStock().setId(transfer.getToStock().getId());
                } else {
                    toStockProd.setQte(toStockProd.getQte() + transferDetails.getQte());
                }
                iStockProductRepository.save(fromStockProd);
                iStockProductRepository.save(toStockProd);
            }
        } else {
            //todo transfer between services
        }


        //todo set transferId to transferDetails move it from here
        transfer.getTransferDetails().forEach(transferDetails -> transferDetails.setTransfer(transfer));

        return CompletableFuture.completedFuture(objectMapper.convertToDto(repository.save(transfer), TransferDto.class));
    }

    @Override
    public CompletableFuture<List<TransferDto>> findAll(Pageable pageable) {
        var x = repository.findAll(pageable).toList();
        List<TransferDto> y = objectMapper.convertToDtoList(x, TransferDto.class);
        return CompletableFuture.completedFuture(y);
    }
}
