////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2020                                                          /
// developed by Abdelilah Dehaoui GitHub : Abdelilah00                         /
////////////////////////////////////////////////////////////////////////////////

package com.alexy.services;

import com.configuration.Exception.UserFriendlyException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface IBaseCrudService<TEntity, TDto, TCreateDto, TUpdateDto> {
    @Async
    CompletableFuture<List<TDto>> findAll() throws ExecutionException, InterruptedException;

    List<TDto> findAll(Sort sort);

    List<TDto> findAll(Example<TDto> example);

    List<TDto> findAll(Example<TDto> example, Sort sort);

    Page<TDto> findAll(Pageable pageable);

    List<TDto> saveAll(Iterable<TDto> entities);

    TDto saveAndFlush(TDto entity);

    void deleteInBatch(Iterable<TDto> entities);

    void deleteAllInBatch();

    TDto getOne(Long aLong) throws InterruptedException;

    @Async
    CompletableFuture<TDto> create(TCreateDto dto) throws UserFriendlyException;

    @Async
    CompletableFuture<TDto> update(TUpdateDto dto);

    @Async
    CompletableFuture<TDto> findById(Long aLong);

    @Async
    void deleteById(Long aLong);

    void delete(TDto entity);

    void deleteAll(Iterable<? extends TDto> entities);

    void deleteAll();

    Optional<TDto> findOne(Example<TDto> example);

    Page<TDto> findAll(Example<TDto> example, Pageable pageable);
}
