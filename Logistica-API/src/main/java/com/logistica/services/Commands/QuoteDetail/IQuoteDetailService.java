package com.logistica.services.Commands.QuoteDetail;

import com.alexy.services.IBaseCrudService;
import com.logistica.domains.Commands.QuoteDetail;
import com.logistica.dtos.Commands.QuoteDetail.QuoteDetailCreateDto;
import com.logistica.dtos.Commands.QuoteDetail.QuoteDetailDto;
import com.logistica.dtos.Commands.QuoteDetail.QuoteDetailUpdateDto;
import org.springframework.stereotype.Service;

public interface IQuoteDetailService extends IBaseCrudService<QuoteDetail, QuoteDetailDto, QuoteDetailCreateDto, QuoteDetailUpdateDto> {

}
