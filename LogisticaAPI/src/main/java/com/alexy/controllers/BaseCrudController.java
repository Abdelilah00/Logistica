////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2020                                                          /
// developed by Abdelilah Dehaoui GitHub : Abdelilah00                         /
////////////////////////////////////////////////////////////////////////////////

package com.alexy.controllers;

import com.configuration.Exception.UserFriendlyException;
import com.alexy.models.BaseDto;
import com.alexy.models.IdEntity;
import com.alexy.services.IBaseCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public abstract class BaseCrudController<TEntity extends IdEntity, TDto extends BaseDto, TCreateDto extends BaseDto, TUpdateDto extends BaseDto> {

    @Autowired
    protected IBaseCrudService<TEntity, TDto, TCreateDto, TUpdateDto> service;

    @RequestMapping(method = RequestMethod.GET)
    protected List<TDto> getAll() throws ExecutionException, InterruptedException {
        return service.findAll().get();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    protected TDto getById(@PathVariable(value = "id") long id) throws InterruptedException, ExecutionException, IOException {
        return service.findById(id).get();
    }

    @RequestMapping(method = RequestMethod.POST)
    protected TDto create(@Valid @RequestBody TCreateDto dto) throws ExecutionException, InterruptedException, UserFriendlyException, IOException, MessagingException {
        return service.create(dto).get();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    protected TDto update(@PathVariable(value = "id") long id, @Valid @RequestBody TUpdateDto dto) throws UserFriendlyException, ExecutionException, InterruptedException {
        if (id != dto.getId())
            throw new UserFriendlyException("Id and model not equals");

        return service.update(dto).get();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    protected void delete(@PathVariable(value = "id") long id) {
        service.deleteById(id);
    }
}
