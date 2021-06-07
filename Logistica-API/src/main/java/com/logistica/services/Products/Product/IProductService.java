package com.logistica.services.Products.Product;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.Product;
import com.logistica.dtos.Products.Product.ProductCreateDto;
import com.logistica.dtos.Products.Product.ProductDto;
import com.logistica.dtos.Products.Product.ProductUpdateDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IProductService extends IBaseCrudService<Product, ProductDto, ProductCreateDto, ProductUpdateDto> {
    CompletableFuture<List<ProductDto>> getByStockId(Long id);

}
