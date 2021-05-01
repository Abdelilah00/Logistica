package com.logistica.controllers.Commands;

import com.alexy.controllers.BaseCrudController;
import com.logistica.domains.Commands.ProposedReviews;
import com.logistica.dtos.Commands.ProposedReviews.ProposedReviewsCreateDto;
import com.logistica.dtos.Commands.ProposedReviews.ProposedReviewsDto;
import com.logistica.dtos.Commands.ProposedReviews.ProposedReviewsUpdateDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/proposedreviewss")
public class ProposedReviewsController extends BaseCrudController<ProposedReviews, ProposedReviewsDto, ProposedReviewsCreateDto, ProposedReviewsUpdateDto> {
}
