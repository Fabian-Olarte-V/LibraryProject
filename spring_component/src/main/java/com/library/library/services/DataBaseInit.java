package com.library.library.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.apache.commons.text.RandomStringGenerator;
import com.library.library.model.Account;
import com.library.library.model.Author;
import com.library.library.model.Book;
import com.library.library.model.Copy;
import com.library.library.model.Favorite;
import com.library.library.repository.AccountRepository;
import com.library.library.repository.AuthorRepository;
import com.library.library.repository.BookRepository;
import com.library.library.repository.CopyRepository;
import com.library.library.repository.FavoritesRepository;
import com.library.library.repository.SaleRepository;
import jakarta.transaction.Transactional;


@Component
public class DataBaseInit implements CommandLineRunner {

  private static final int NUM_BOOKS = 100;
  private static final int NUM_PERSONS = 5;
  private static final int NUM_COPIES = 10;

  @Autowired
  AccountRepository accountRepository;

  @Autowired
  AuthorRepository authorRepository;

  @Autowired
  BookRepository bookRepository;

  @Autowired
  CopyRepository copyRepository;

  @Autowired
  SaleRepository saleRepository;

  @Autowired
  FavoritesRepository favoritesRepository;

  @Override
  @Transactional
  public void run(String... args) throws Exception {

    List<String> categories = new ArrayList<>();
    categories.add("Drama");
    categories.add("Comedia");
    categories.add("Aventura");

    Random random = new Random(1234);
    RandomStringGenerator randomGenerator = new RandomStringGenerator.Builder().withinRange('a', 'z').usingRandom(random::nextInt).build();

    for(int i=0; i<NUM_BOOKS;i++){

      Book book = new Book(randomGenerator.generate(5, 15),
                            random.nextInt(9999), random.nextInt(1000), 
                            randomGenerator.generate(10, 15),
                            categories.get(random.nextInt(3)), 
                            randomGenerator.generate(30, 60), 
                            randomGenerator.generate(10, 15), 
                            random.nextInt(5)+1);

      

      int numAuthors = random.nextInt(NUM_PERSONS-1) +1;
      ArrayList<Author> authors = new ArrayList<>();
      for(int j = 0; j<numAuthors; j++){
        Author author = new Author(randomGenerator.generate(5, 15), randomGenerator.generate(5, 10), randomGenerator.generate(5, 150));
        authorRepository.save(author);
        authors.add(author);
      }

      int numCopies = random.nextInt(NUM_COPIES-1)+1;
      ArrayList<Copy> copies = new ArrayList<>();
      for(int h = 0; h<numCopies; h++){
        Copy copy = new Copy(484864, "gfgrerg", random.nextInt(1000), book);
        copyRepository.save(copy);
        copies.add(copy);
      }

      book.setAuthors(authors);
      book.setCopies(copies);
      bookRepository.save(book);
    }

    String password = new BCryptPasswordEncoder().encode("admin");
    Account account = new Account("Manuel", "Admin", "manuel@gmail.com", password);

    accountRepository.save(account);

    Account auxA = accountRepository.findById(Long.parseLong("1")).orElseThrow();
    Book auxB = bookRepository.findById(Long.valueOf("1")).orElseThrow();
    Book auxR = bookRepository.findById(Long.valueOf("2")).orElseThrow();
    Favorite auxC = new Favorite(auxA, auxB);
    Favorite auxH = new Favorite(auxA, auxR);
    favoritesRepository.save(auxC);
    favoritesRepository.save(auxH);

    //AÑADIR AÑO DE PUBLICACION PARA EL LIBRO
  }
  
}
