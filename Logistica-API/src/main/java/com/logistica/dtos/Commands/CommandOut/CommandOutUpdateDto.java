package com.logistica.dtos.Commands.CommandOut;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommandOutUpdateDto extends BaseDto {
    private String Supplier;
    private String CommandDetail;
    private String description;
    private String Needs;
    private String PayementMethod;
}
