package com.logistica.services.Commands.Quote;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Commands.Quote;
import com.logistica.dtos.Commands.Quote.QuoteCreateDto;
import com.logistica.dtos.Commands.Quote.QuoteDto;
import com.logistica.dtos.Commands.Quote.QuoteUpdateDto;
import org.springframework.stereotype.Service;

public interface IQuoteService extends IBaseCrudService<Quote, QuoteDto, QuoteCreateDto, QuoteUpdateDto> {

}
