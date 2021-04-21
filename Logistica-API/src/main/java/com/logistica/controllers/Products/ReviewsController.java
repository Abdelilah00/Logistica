package com.logistica.controllers.Products;

import com.alexy.controllers.BaseCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.logistica.domains.Products.Reviews;
import com.logistica.dtos.Products.Reviews.ReviewsCreateDto;
import com.logistica.dtos.Products.Reviews.ReviewsDto;
import com.logistica.dtos.Products.Reviews.ReviewsUpdateDto;

@RestController
@RequestMapping("api/reviewss")
public class ReviewsController extends BaseCrudController<Reviews, ReviewsDto, ReviewsCreateDto, ReviewsUpdateDto> {
}
