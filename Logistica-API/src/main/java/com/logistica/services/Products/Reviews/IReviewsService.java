package com.logistica.services.Products.Reviews;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Products.Reviews;
import com.logistica.dtos.Products.Reviews.ReviewsCreateDto;
import com.logistica.dtos.Products.Reviews.ReviewsDto;
import com.logistica.dtos.Products.Reviews.ReviewsUpdateDto;
import org.springframework.stereotype.Service;

public interface IReviewsService extends IBaseCrudService<Reviews, ReviewsDto, ReviewsCreateDto, ReviewsUpdateDto> {

}
