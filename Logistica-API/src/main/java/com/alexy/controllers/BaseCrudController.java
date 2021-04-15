////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2020                                                          /
// developed by Abdelilah Dehaoui GitHub : Abdelilah00                         /
////////////////////////////////////////////////////////////////////////////////

package com.alexy.controllers;

import com.alexy.models.BaseDto;
import com.alexy.models.IdEntity;
import com.alexy.services.IBaseCrudService;
import com.configuration.Exception.UserFriendlyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;

public abstract class BaseCrudController<TEntity extends IdEntity, TDto extends BaseDto, TCreateDto extends BaseDto, TUpdateDto extends BaseDto> {

    @Autowired
    protected IBaseCrudService<TEntity, TDto, TCreateDto, TUpdateDto> service;

    /*?page=1&size=200&sort=id,desc&value=9*/

    @GetMapping
    protected List<TDto> getAll(@PageableDefault(page = 0, size = 20) Pageable pageable) throws ExecutionException, InterruptedException {
        return service.findAll(pageable).get();
    }

    @GetMapping(value = "{id}")
    protected TDto getById(@PathVariable(value = "id") long id) throws InterruptedException, ExecutionException {
        return service.findById(id).get();
    }

    @PostMapping
    protected TDto create(@Valid @RequestBody TCreateDto dto) throws ExecutionException, InterruptedException, UserFriendlyException {
        return service.create(dto).get();
    }

    @PutMapping(value = "{id}")
    protected TDto update(@PathVariable(value = "id") long id, @Valid @RequestBody TUpdateDto dto) throws UserFriendlyException, ExecutionException, InterruptedException {
        if (id != dto.getId())
            throw new UserFriendlyException("Id and model not equals");
        return service.update(dto).get();
    }

    @DeleteMapping(value = "{id}")
    protected void delete(@PathVariable(value = "id") long id) {
        service.deleteById(id);
    }
}
