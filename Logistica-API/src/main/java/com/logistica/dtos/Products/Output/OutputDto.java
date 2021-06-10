package com.logistica.dtos.Products.Output;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OutputDto extends BaseDto {
    private String ref;
    private String distination;
    private String askBy;
    private String DistinationType;
    private String actorName;
    private String description;
    private Date date;
    private Boolean intern;
}
