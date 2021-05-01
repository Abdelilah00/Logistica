package com.logistica.repositories.Commands;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Commands.Quote;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuoteRepository extends IBaseJpaRepository<Quote> {
}
