package com.logistica.dtos.Products.Reviews;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewsUpdateDto extends BaseDto {
    private String stars;
    private String Product;
}
