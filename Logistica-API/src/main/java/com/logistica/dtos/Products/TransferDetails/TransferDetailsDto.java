package com.logistica.dtos.Products.TransferDetails;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferDetailsDto extends BaseDto {
    private Long productId;
    private Integer qte;
}
