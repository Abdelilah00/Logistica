package com.logistica.repositories.Commands;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Commands.ProposedReviews;
import org.springframework.stereotype.Repository;

@Repository
public interface IProposedReviewsRepository extends IBaseJpaRepository<ProposedReviews> {
}
