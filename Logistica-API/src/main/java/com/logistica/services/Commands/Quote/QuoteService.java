package com.logistica.services.Commands.Quote;

import com.alexy.services.BaseCrudServiceImpl;
import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Commands.Quote;
import com.logistica.dtos.Commands.Quote.QuoteCreateDto;
import com.logistica.dtos.Commands.Quote.QuoteDto;
import com.logistica.dtos.Commands.Quote.QuoteUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class QuoteService extends BaseCrudServiceImpl<Quote, QuoteDto, QuoteCreateDto, QuoteUpdateDto> implements IQuoteService {

    public QuoteService() {
        super(Quote.class, QuoteDto.class, QuoteCreateDto.class, QuoteUpdateDto.class);
    }
}
