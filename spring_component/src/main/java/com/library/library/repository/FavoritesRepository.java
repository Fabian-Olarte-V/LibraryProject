package com.library.library.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.library.library.model.Favorite;


public interface FavoritesRepository extends CrudRepository<Favorite, Long> {
    
    @Query
    (value = "SELECT * FROM FAVORITE as t WHERE t.user_id = ?1 AND t.favorite_book_id = ?2", nativeQuery = true)
    Optional<Favorite> findByBookIdAndUserId(Long userId, Long bookId);

}
