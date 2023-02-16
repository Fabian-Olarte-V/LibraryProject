package com.library.library.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.library.library.model.Book;


public interface BookRepository extends CrudRepository<Book, Long> {

    @Query(value = "SELECT * FROM BOOK as b WHERE b.category IN ?1 ORDER BY b.score DESC LIMIT 15", nativeQuery = true)
    List<Book> findRecommendedBooks(List<String> categories);

}
