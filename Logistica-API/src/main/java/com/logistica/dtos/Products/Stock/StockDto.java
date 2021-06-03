package com.logistica.dtos.Products.Stock;

import com.alexy.models.BaseDto;
import com.logistica.dtos.Products.Product.ProductDto;
import com.logistica.dtos.Products.StockProduct.StockProductDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StockDto extends BaseDto {
    private String name;
    private String adresse;
    private String area;
    private String responsibleName;
    private String type;

    private Integer qteByProduct;
    private Integer qteOfProducts;
}
