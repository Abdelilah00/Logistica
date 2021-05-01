package com.logistica.repositories.Commands;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Commands.QuoteDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuoteDetailRepository extends IBaseJpaRepository<QuoteDetail> {
}
