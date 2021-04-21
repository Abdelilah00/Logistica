package com.logistica.repositories.Commands;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Commands.Quote;

@Repository
public interface IQuoteRepository extends IBaseJpaRepository<Quote> {
}
