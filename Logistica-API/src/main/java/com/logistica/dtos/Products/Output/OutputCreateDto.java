package com.logistica.dtos.Products.Output;

import com.alexy.models.BaseDto;
import com.logistica.dtos.Products.TransactionDetail.TransactionDetailCreateDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OutputCreateDto extends BaseDto {
    private String ref;
    private String distination;
    private String askBy;
    private String DistinationType;
    private Long actorId;
    private String description;
    private Date date;

    @NotEmpty
    private List<TransactionDetailCreateDto> transactionDetails = new ArrayList<>();
}
