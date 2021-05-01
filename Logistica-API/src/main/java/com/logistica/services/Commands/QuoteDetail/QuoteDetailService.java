package com.logistica.services.Commands.QuoteDetail;

import com.alexy.services.BaseCrudServiceImpl;
import com.logistica.domains.Commands.QuoteDetail;
import com.logistica.dtos.Commands.QuoteDetail.QuoteDetailCreateDto;
import com.logistica.dtos.Commands.QuoteDetail.QuoteDetailDto;
import com.logistica.dtos.Commands.QuoteDetail.QuoteDetailUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class QuoteDetailService extends BaseCrudServiceImpl<QuoteDetail, QuoteDetailDto, QuoteDetailCreateDto, QuoteDetailUpdateDto> implements IQuoteDetailService {

    public QuoteDetailService() {
        super(QuoteDetail.class, QuoteDetailDto.class, QuoteDetailCreateDto.class, QuoteDetailUpdateDto.class);
    }
}
