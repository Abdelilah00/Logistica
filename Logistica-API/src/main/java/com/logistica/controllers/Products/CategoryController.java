package com.logistica.controllers.Products;

import com.alexy.controllers.BaseCrudController;
import com.logistica.domains.Products.Category;
import com.logistica.dtos.Products.Category.CategoryCreateDto;
import com.logistica.dtos.Products.Category.CategoryDto;
import com.logistica.dtos.Products.Category.CategoryUpdateDto;
import com.logistica.services.Products.Category.ICategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("api/categories")
public class CategoryController extends BaseCrudController<Category, CategoryDto, CategoryCreateDto, CategoryUpdateDto> {

    @GetMapping(path = "/getByParentId/{id}")
    protected List<CategoryDto> getByParentId(@PathVariable(value = "id") Long id) throws ExecutionException, InterruptedException {
        return ((ICategoryService) service).getByParentId(id).get();
    }

    @GetMapping(path = "/getParents")
    protected List<CategoryDto> getParents() throws ExecutionException, InterruptedException {
        return ((ICategoryService) service).getParents().get();
    }

    @GetMapping(path = "/getChildren")
    protected List<CategoryDto> getChildren() throws ExecutionException, InterruptedException {
        return ((ICategoryService) service).getChildren().get();
    }
}
