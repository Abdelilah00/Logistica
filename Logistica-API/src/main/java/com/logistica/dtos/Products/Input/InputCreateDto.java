package com.logistica.dtos.Products.Input;

import com.alexy.models.BaseDto;
import com.logistica.dtos.Products.TransactionDetail.TransactionDetailCreateDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class InputCreateDto extends BaseDto {
    @NotBlank
    private String ref;
    private Long actorId;
    private Date date;
    private String description;
    @NotEmpty
    private List<TransactionDetailCreateDto> transactionDetails = new ArrayList<>();
}
