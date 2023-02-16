package com.library.library.repository;

import org.springframework.data.repository.CrudRepository;
import com.library.library.model.Sale;


public interface SaleRepository extends CrudRepository<Sale, Long> {
    
}
