package com.logistica.dtos.Commands.Offer;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferDto extends BaseDto {
    private String dateStart;
    private String dateEnd;
    private String Status;
    private String Needs;
    private String OffreDetail;
}
