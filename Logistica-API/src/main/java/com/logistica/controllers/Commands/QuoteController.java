package com.logistica.controllers.Commands;

import com.alexy.controllers.BaseCrudController;
import com.logistica.domains.Commands.Quote;
import com.logistica.dtos.Commands.Quote.QuoteCreateDto;
import com.logistica.dtos.Commands.Quote.QuoteDto;
import com.logistica.dtos.Commands.Quote.QuoteUpdateDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/quotes")
public class QuoteController extends BaseCrudController<Quote, QuoteDto, QuoteCreateDto, QuoteUpdateDto> {
}
