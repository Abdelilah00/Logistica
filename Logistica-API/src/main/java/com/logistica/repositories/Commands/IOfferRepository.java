package com.logistica.repositories.Commands;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Commands.Offer;
import org.springframework.stereotype.Repository;

@Repository
public interface IOfferRepository extends IBaseJpaRepository<Offer> {
}
