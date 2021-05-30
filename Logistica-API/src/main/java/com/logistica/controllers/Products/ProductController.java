package com.logistica.controllers.Products;

import com.alexy.controllers.BaseCrudController;
import com.logistica.domains.Products.Product;
import com.logistica.dtos.Products.Product.ProductCreateDto;
import com.logistica.dtos.Products.Product.ProductDto;
import com.logistica.dtos.Products.Product.ProductUpdateDto;
import com.logistica.services.Products.Product.IProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/products")
public class ProductController extends BaseCrudController<Product, ProductDto, ProductCreateDto, ProductUpdateDto> {
    @GetMapping(path = "/getByStockId/{id}")
    protected List<ProductDto> getByStockId(@PathVariable(value = "id") Long id) throws ExecutionException, InterruptedException {
        return ((IProductService) service).getByStockId(id).get();
    }
}
