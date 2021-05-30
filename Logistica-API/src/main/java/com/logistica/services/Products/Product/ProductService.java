package com.logistica.services.Products.Product;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Products.Product;
import com.logistica.dtos.Products.Product.ProductCreateDto;
import com.logistica.dtos.Products.Product.ProductDto;
import com.logistica.dtos.Products.Product.ProductUpdateDto;
import com.logistica.dtos.Products.TransactionDetail.TransactionDetailDto;
import com.logistica.repositories.Products.IProductRepository;
import com.logistica.repositories.Products.ITransactionDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductService extends BaseCrudServiceImpl<Product, ProductDto, ProductCreateDto, ProductUpdateDto> implements IProductService {

    public ProductService() {
        super(Product.class, ProductDto.class, ProductCreateDto.class, ProductUpdateDto.class);
    }

    @Override
    public CompletableFuture<List<ProductDto>> getByStockId(Long id) {
        var tmp = ((IProductRepository) repository).getByStockProductsStockId(id);
        return CompletableFuture.completedFuture(objectMapper.convertToDtoList(tmp, ProductDto.class));
    }
}
