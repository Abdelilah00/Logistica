package com.logistica.services.Products.Product;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Products.Product;
import com.logistica.dtos.Products.Product.ProductCreateDto;
import com.logistica.dtos.Products.Product.ProductDto;
import com.logistica.dtos.Products.Product.ProductUpdateDto;
import com.logistica.repositories.Products.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class ProductService extends BaseCrudServiceImpl<Product, ProductDto, ProductCreateDto, ProductUpdateDto> implements IProductService {

    public ProductService() {
        super(Product.class, ProductDto.class, ProductCreateDto.class, ProductUpdateDto.class);
    }

    @Override
    public CompletableFuture<List<ProductDto>> getByStockId(Long id) {
        var tmp = ((IProductRepository) repository).getByStockProductsStockId(id);
        List<ProductDto> tmpDto = objectMapper.convertToDtoList(tmp, ProductDto.class);
        for (int i = 0; i < tmp.size(); i++) {
            tmpDto.get(i).setQteByStock(tmp.get(i).getStockProducts().stream().filter(f -> f.getStock().getId() == id).collect(Collectors.toList()).get(0).getQte());
        }
        return CompletableFuture.completedFuture(tmpDto);
    }
}
