package com.logistica.services.Products.Category;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.Category;
import com.logistica.dtos.Products.Category.CategoryCreateDto;
import com.logistica.dtos.Products.Category.CategoryDto;
import com.logistica.dtos.Products.Category.CategoryUpdateDto;

public interface ICategoryService extends IBaseCrudService<Category, CategoryDto, CategoryCreateDto, CategoryUpdateDto> {

}
