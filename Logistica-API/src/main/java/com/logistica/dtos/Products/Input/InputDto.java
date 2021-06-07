package com.logistica.dtos.Products.Input;

import com.alexy.models.BaseDto;
import com.logistica.dtos.Products.TransactionDetail.InputDetailDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class InputDto extends BaseDto {
    private String ref;
    private String actorName;
    private Date date;
    private String description;

    private List<InputDetailDto> inputDetailDtos;
}
