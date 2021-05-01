package com.logistica.dtos.Products.Input;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class InputUpdateDto extends BaseDto {
    private String supplierName;
    private Date date;
    private String description;
}
