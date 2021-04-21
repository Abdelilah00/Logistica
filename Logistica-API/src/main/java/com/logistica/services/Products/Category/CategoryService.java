package com.logistica.services.Products.Category;

import com.alexy.services.BaseCrudServiceImpl;
import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.Category;
import com.logistica.dtos.Products.Category.CategoryCreateDto;
import com.logistica.dtos.Products.Category.CategoryDto;
import com.logistica.dtos.Products.Category.CategoryUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends BaseCrudServiceImpl<Category, CategoryDto, CategoryCreateDto, CategoryUpdateDto> implements ICategoryService {

    public CategoryService() {
        super(Category.class, CategoryDto.class, CategoryCreateDto.class, CategoryUpdateDto.class);
    }
}
