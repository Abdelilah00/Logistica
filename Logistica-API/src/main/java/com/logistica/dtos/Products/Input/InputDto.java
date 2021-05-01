package com.logistica.dtos.Products.Input;

import com.alexy.models.BaseDto;
import com.logistica.dtos.Products.TransactionDetail.TransactionDetailDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class InputDto extends BaseDto {
    private String supplierName;
    private Date date;
    private String description;

    private List<TransactionDetailDto> transactionDetailDtos;
}
