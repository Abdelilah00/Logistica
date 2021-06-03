package com.logistica.dtos.Products.Transfer;

import com.alexy.models.BaseDto;
import com.logistica.domains.Products.TransferDetails;
import com.logistica.dtos.Products.TransferDetails.TransferDetailsDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TransferDto extends BaseDto {
    private Date date;
    private Integer delay;
    private String fromStockName;
    private String toStockName;
    private String actorName;
}
