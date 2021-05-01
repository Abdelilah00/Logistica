package com.logistica.services.Products.Reviews;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Products.Reviews;
import com.logistica.dtos.Products.Reviews.ReviewsCreateDto;
import com.logistica.dtos.Products.Reviews.ReviewsDto;
import com.logistica.dtos.Products.Reviews.ReviewsUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class ReviewsService extends BaseCrudServiceImpl<Reviews, ReviewsDto, ReviewsCreateDto, ReviewsUpdateDto> implements IReviewsService {

    public ReviewsService() {
        super(Reviews.class, ReviewsDto.class, ReviewsCreateDto.class, ReviewsUpdateDto.class);
    }
}
