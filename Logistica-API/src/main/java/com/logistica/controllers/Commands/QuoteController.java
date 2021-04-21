package com.logistica.controllers.Commands;

import com.alexy.controllers.BaseCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.logistica.domains.Commands.Quote;
import com.logistica.dtos.Commands.Quote.QuoteCreateDto;
import com.logistica.dtos.Commands.Quote.QuoteDto;
import com.logistica.dtos.Commands.Quote.QuoteUpdateDto;

@RestController
@RequestMapping("api/quotes")
public class QuoteController extends BaseCrudController<Quote, QuoteDto, QuoteCreateDto, QuoteUpdateDto> {
}
