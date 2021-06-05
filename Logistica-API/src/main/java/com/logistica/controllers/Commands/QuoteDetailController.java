package com.logistica.controllers.Commands;

import com.alexy.controllers.BaseCrudController;
import com.logistica.domains.Commands.QuoteDetail;
import com.logistica.dtos.Commands.QuoteDetail.QuoteDetailCreateDto;
import com.logistica.dtos.Commands.QuoteDetail.QuoteDetailDto;
import com.logistica.dtos.Commands.QuoteDetail.QuoteDetailUpdateDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/quoteDetails")
public class QuoteDetailController extends BaseCrudController<QuoteDetail, QuoteDetailDto, QuoteDetailCreateDto, QuoteDetailUpdateDto> {
}
