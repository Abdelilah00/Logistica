package com.logistica.services.Commands.ProposedReviews;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Commands.ProposedReviews;
import com.logistica.dtos.Commands.ProposedReviews.ProposedReviewsCreateDto;
import com.logistica.dtos.Commands.ProposedReviews.ProposedReviewsDto;
import com.logistica.dtos.Commands.ProposedReviews.ProposedReviewsUpdateDto;
import org.springframework.stereotype.Service;

public interface IProposedReviewsService extends IBaseCrudService<ProposedReviews, ProposedReviewsDto, ProposedReviewsCreateDto, ProposedReviewsUpdateDto> {

}
