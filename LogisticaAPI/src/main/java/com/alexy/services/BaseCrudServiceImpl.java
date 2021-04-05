////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2020                                                          /
// developed by Abdelilah Dehaoui GitHub : Abdelilah00                         /
////////////////////////////////////////////////////////////////////////////////

package com.alexy.services;

import com.configuration.Exception.UserFriendlyException;
import com.alexy.models.BaseDto;
import com.alexy.models.BaseEntity;
import com.alexy.repositorys.IBaseJpaRepository;
import com.alexy.utilis.ModelEntityMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Transactional
public class BaseCrudServiceImpl<TEntity extends BaseEntity,
        TDto extends BaseDto,
        TCreateDto extends BaseDto,
        TUpdateDto extends BaseDto>
        implements IBaseCrudService<TEntity, TDto, TCreateDto, TUpdateDto> {

    @Autowired
    protected IBaseJpaRepository<TEntity> repository;
    protected ModelEntityMapping<TEntity> objectMapper = new ModelEntityMapping<>();
    private final Class<TDto> dtoClass;
    private final Class<TCreateDto> dtoCreateClass;
    private final Class<TUpdateDto> dtoUpdateClass;

    public BaseCrudServiceImpl(Class<TEntity> tEntityClass, Class<TDto> dtoClass, Class<TCreateDto> dtoCreateClass, Class<TUpdateDto> dtoUpdateClass) {
        objectMapper.setEntityClass(tEntityClass);
        this.dtoClass = dtoClass;
        this.dtoCreateClass = dtoCreateClass;
        this.dtoUpdateClass = dtoUpdateClass;
    }

    @Override
    public CompletableFuture<List<TDto>> findAll() {
        var x = repository.findAll();
        return CompletableFuture.completedFuture(objectMapper.convertToDtoList(x, dtoClass));
    }

    @Override
    public TDto getOne(Long aLong) {
        return null;
    }

    @Override
    public Page<TDto> findAll(Pageable pageable) {
        return (null);
    }

    @Override
    public CompletableFuture<TDto> create(TCreateDto entity) throws UserFriendlyException {
        var tmp = objectMapper.convertToEntity(entity);
        return CompletableFuture.completedFuture(objectMapper.convertToDto(repository.save(tmp), dtoClass));
    }

    @Override
    public CompletableFuture<TDto> update(TUpdateDto entity) {
        return CompletableFuture.completedFuture(objectMapper.convertToDto(repository.save(objectMapper.convertToEntity(entity)), dtoClass));
    }

    @Override
    public CompletableFuture<TDto> findById(Long aLong) {
        return CompletableFuture.completedFuture(objectMapper.convertToDto(repository.findById(aLong).get(), dtoClass));
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public List<TDto> findAll(Sort sort) {
        return (null);
    }

    @Override
    public List<TDto> saveAll(Iterable<TDto> entities) {
        return (null);
    }

    @Override
    public TDto saveAndFlush(TDto entity) {
        return (null);
    }

    @Override
    public void deleteInBatch(Iterable<TDto> entities) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public List<TDto> findAll(Example<TDto> example) {
        return (null);
    }

    @Override
    public List<TDto> findAll(Example<TDto> example, Sort sort) {
        return (null);
    }


    @Override
    public void delete(TDto entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends TDto> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Optional<TDto> findOne(Example<TDto> example) {
        return null;
    }

    @Override
    public Page<TDto> findAll(Example<TDto> example, Pageable pageable) {
        return (null);
    }
}
