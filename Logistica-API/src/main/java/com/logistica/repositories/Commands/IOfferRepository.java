package com.logistica.repositories.Commands;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Commands.Offer;

@Repository
public interface IOfferRepository extends IBaseJpaRepository<Offer> {
}
