package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Products.Reviews;
import org.springframework.stereotype.Repository;

@Repository
public interface IReviewsRepository extends IBaseJpaRepository<Reviews> {
}
