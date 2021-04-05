////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2020                                                          /
// developed by Abdelilah Dehaoui GitHub : Abdelilah00                         /
////////////////////////////////////////////////////////////////////////////////

package com.alexy.utilis;

import com.alexy.models.BaseDto;
import com.alexy.models.IdEntity;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Setter
public class ModelEntityMapping<TEntity extends IdEntity> extends ModelMapper {

    private Class<TEntity> entityClass;

    public <T extends BaseDto> List<T> convertToDtoList(List<TEntity> entityList, Class<? extends BaseDto> dtoClass) {
        List<T> list = new ArrayList<>();
        for (TEntity entity : entityList) {
            T tDtoCompletableFuture = convertToDto(entity, dtoClass);
            list.add(tDtoCompletableFuture);
        }
        return (list);
    }

    public <T extends BaseDto> List<TEntity> convertToEntityList(List<T> dtoList) {
        List<TEntity> list = new ArrayList<>();
        for (T tDto : dtoList) {
            TEntity tEntityCompletableFuture = convertToEntity(tDto);
            list.add(tEntityCompletableFuture);
        }
        return (list);
    }

    public <T extends BaseDto> T convertToDto(@NotNull TEntity entity, Class<? extends BaseDto> dtoClass) {
        return map(entity, (Type) dtoClass);
    }

    public <T extends BaseDto> TEntity convertToEntity(@NotNull T dto) {
        return map(dto, entityClass);
    }
}
