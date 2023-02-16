package com.library.library.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.library.library.model.ShoppingCart;


public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {
    
    @Query
    (value = "SELECT * FROM SHOPPING_CART as t WHERE t.user_id = ?1 AND t.added_copy_id = ?2", nativeQuery = true)
    ShoppingCart findByUserIdAndCopyId(Long userId, Long copyId);
    
}
