package com.logistica.services.Products.Category;

import com.alexy.services.BaseCrudServiceImpl;
import com.configuration.Exception.UserFriendlyException;
import com.logistica.domains.Products.Category;
import com.logistica.dtos.Products.Category.CategoryCreateDto;
import com.logistica.dtos.Products.Category.CategoryDto;
import com.logistica.dtos.Products.Category.CategoryUpdateDto;
import com.logistica.repositories.Products.ICategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CategoryService extends BaseCrudServiceImpl<Category, CategoryDto, CategoryCreateDto, CategoryUpdateDto> implements ICategoryService {

    public CategoryService() {
        super(Category.class, CategoryDto.class, CategoryCreateDto.class, CategoryUpdateDto.class);
    }

    @Override
    public CompletableFuture<CategoryDto> create(CategoryCreateDto entity) throws UserFriendlyException {
        var tmp = objectMapper.convertToEntity(entity);
        if (entity.getParentId() != null) {
            var cat = new Category();
            cat.setId(entity.getParentId());
            tmp.setParent(cat);
        }
        return CompletableFuture.completedFuture(objectMapper.convertToDto(repository.save(tmp), CategoryDto.class));
    }

    @Override
    public CompletableFuture<List<CategoryDto>> getByParentId(Long id) {
        return CompletableFuture.completedFuture(objectMapper.convertToDtoList(((ICategoryRepository) repository).getByParentId(id), CategoryDto.class));
    }

    @Override
    public CompletableFuture<List<CategoryDto>> getParents() {
        return CompletableFuture.completedFuture(objectMapper.convertToDtoList(((ICategoryRepository) repository).getByParentIsNull(), CategoryDto.class));
    }

    @Override
    public CompletableFuture<List<CategoryDto>> getChildren() {
        return CompletableFuture.completedFuture(objectMapper.convertToDtoList(((ICategoryRepository) repository).getByParentIsNotNull(), CategoryDto.class));
    }
}
