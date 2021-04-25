package com.logistica.dtos.Products.Input;

import com.alexy.models.BaseDto;
import com.logistica.dtos.Products.TransactionDetail.TransactionDetailCreateDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InputCreateDto extends BaseDto {
    private String acteur;
    private String ActeurType;
    private String Stage;
    private List<TransactionDetailCreateDto> transactionDetailCreateDtos;
}
