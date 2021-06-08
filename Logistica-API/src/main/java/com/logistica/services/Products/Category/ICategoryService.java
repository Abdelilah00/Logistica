package com.logistica.services.Products.Category;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.Category;
import com.logistica.dtos.Products.Category.CategoryCreateDto;
import com.logistica.dtos.Products.Category.CategoryDto;
import com.logistica.dtos.Products.Category.CategoryUpdateDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ICategoryService extends IBaseCrudService<Category, CategoryDto, CategoryCreateDto, CategoryUpdateDto> {
    CompletableFuture<List<CategoryDto>> getByParentId(Long id);

    CompletableFuture<List<CategoryDto>> getParents();

    CompletableFuture<List<CategoryDto>> getChildren();
}
