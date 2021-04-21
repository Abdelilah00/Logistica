package com.logistica.services.Commands.ProposedReviews;

import com.alexy.services.BaseCrudServiceImpl;
import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Commands.ProposedReviews;
import com.logistica.dtos.Commands.ProposedReviews.ProposedReviewsCreateDto;
import com.logistica.dtos.Commands.ProposedReviews.ProposedReviewsDto;
import com.logistica.dtos.Commands.ProposedReviews.ProposedReviewsUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class ProposedReviewsService extends BaseCrudServiceImpl<ProposedReviews, ProposedReviewsDto, ProposedReviewsCreateDto, ProposedReviewsUpdateDto> implements IProposedReviewsService {

    public ProposedReviewsService() {
        super(ProposedReviews.class, ProposedReviewsDto.class, ProposedReviewsCreateDto.class, ProposedReviewsUpdateDto.class);
    }
}
