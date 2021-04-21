package com.logistica.repositories.Commands;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Commands.QuoteDetail;

@Repository
public interface IQuoteDetailRepository extends IBaseJpaRepository<QuoteDetail> {
}
