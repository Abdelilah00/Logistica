package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Products.OutputDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOutputDetailRepository extends IBaseJpaRepository<OutputDetails> {
    List<OutputDetails> getByOutputId(Long id);
}
