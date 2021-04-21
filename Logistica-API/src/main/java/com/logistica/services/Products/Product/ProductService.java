package com.logistica.services.Products.Product;

import com.alexy.services.BaseCrudServiceImpl;
import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.Product;
import com.logistica.dtos.Products.Product.ProductCreateDto;
import com.logistica.dtos.Products.Product.ProductDto;
import com.logistica.dtos.Products.Product.ProductUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseCrudServiceImpl<Product, ProductDto, ProductCreateDto, ProductUpdateDto> implements IProductService {

    public ProductService() {
        super(Product.class, ProductDto.class, ProductCreateDto.class, ProductUpdateDto.class);
    }
}
