package com.logistica.dtos.Products.Transfer;

import com.alexy.models.BaseDto;
import com.logistica.domains.Organ.StructureUnit;
import com.logistica.domains.Products.Stock;
import com.logistica.dtos.Products.TransferDetails.TransferDetailsDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TransferCreateDto extends BaseDto {
    private String ref;
    private String description;
    private Date date;
    private Integer delay;
    private Long actorId;
    private Long fromStockId;
    private Long toStockId;
    private Long fromStructureUnitId;
    private Long toStructureUnitId;

    private List<TransferDetailsDto> transferDetails;
}
