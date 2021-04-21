package com.logistica.dtos.Commands.Quote;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteCreateDto extends BaseDto {
private String expDate;
private String Supplier;
private String Offer;
private String QuoteStatus;
}
