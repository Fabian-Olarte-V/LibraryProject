package com.library.library.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.library.library.model.Account;


public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findByEmail(String email);
    
}
