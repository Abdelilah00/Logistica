package com.logistica.controllers.Products;

import com.alexy.controllers.BaseCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.logistica.domains.Products.Product;
import com.logistica.dtos.Products.Product.ProductCreateDto;
import com.logistica.dtos.Products.Product.ProductDto;
import com.logistica.dtos.Products.Product.ProductUpdateDto;

@RestController
@RequestMapping("api/products")
public class ProductController extends BaseCrudController<Product, ProductDto, ProductCreateDto, ProductUpdateDto> {
}
