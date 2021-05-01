package com.logistica.dtos.Commands.ProposedReviews;

import com.alexy.models.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProposedReviewsDto extends BaseDto {
    private String productProposedName;
    private String stars;
}
