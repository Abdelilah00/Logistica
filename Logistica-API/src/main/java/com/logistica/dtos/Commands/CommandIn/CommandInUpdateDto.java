package com.logistica.dtos.Commands.CommandIn;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommandInUpdateDto extends BaseDto {
private String ref;
private String description;
private String Client;
private String Service;
private String PayementMethod;
private String CommandDetail;
}
