package com.logistica.dtos.Products.Transfer;

import com.alexy.models.BaseDto;
import com.logistica.dtos.Products.TransferDetails.TransferDetailsDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TransferUpdateDto extends BaseDto {
    private String ref;
    private String description;
    private Date date;
    private Integer delay;
    private String fromStockId;
    private String toStockId;
    private Long actorId;

    private List<TransferDetailsDto> transferDetails;
}
