package com.logistica.dtos.Commands.QuoteDetail;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteDetailCreateDto extends BaseDto {
private String qte;
private String unitPrice;
private String productName;
private String productProposedName;
private String Quote;
}
