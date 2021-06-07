package com.logistica.repositories.Products;

import com.alexy.repositories.IBaseJpaRepository;
import com.logistica.domains.Products.InputDetails;
import com.logistica.domains.Products.OutputDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInputDetailRepository extends IBaseJpaRepository<InputDetails> {
    List<InputDetails> getByInputId(Long id);

}

