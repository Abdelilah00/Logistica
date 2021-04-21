package com.logistica.repositories.Commands;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Commands.ProposedReviews;

@Repository
public interface IProposedReviewsRepository extends IBaseJpaRepository<ProposedReviews> {
}
