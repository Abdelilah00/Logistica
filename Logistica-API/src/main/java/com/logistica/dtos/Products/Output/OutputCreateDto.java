package com.logistica.dtos.Products.Output;

import com.alexy.models.BaseDto;
import com.logistica.dtos.Products.TransactionDetail.InputDetailCreateDto;
import com.logistica.dtos.Products.TransactionDetail.OutputDetailCreateDto;
import com.logistica.dtos.Products.TransactionDetail.OutputDetailDto;
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
    private Boolean intern;

    @NotEmpty
    private List<OutputDetailCreateDto> outputDetails = new ArrayList<>();
}
