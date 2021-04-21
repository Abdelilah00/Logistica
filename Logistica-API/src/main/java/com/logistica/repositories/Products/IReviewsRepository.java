package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import org.springframework.stereotype.Repository;
import com.logistica.domains.Products.Reviews;

@Repository
public interface IReviewsRepository extends IBaseJpaRepository<Reviews> {
}
