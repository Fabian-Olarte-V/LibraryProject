package com.library.library.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.library.library.DTO.BookFullInformationDTO;
import com.library.library.model.Account;
import com.library.library.model.Book;
import com.library.library.model.Copy;
import com.library.library.model.Favorite;
import com.library.library.model.Sale;
import com.library.library.model.ShoppingCart;
import com.library.library.repository.AccountRepository;
import com.library.library.repository.FavoritesRepository;
import com.library.library.repository.ShoppingCartRepository;
import jakarta.transaction.Transactional;


@Service
public class UserService {

    @Lazy
    @Autowired
    LibraryService libraryService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    FavoritesRepository favoritesRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    public Account getAccountByEmail(String email){
        return accountRepository.findByEmail(email).orElseThrow();
    }

    public List<BookFullInformationDTO> getRecommendedBooksByUserId(long userId){
        Account account = accountRepository.findById(userId).orElseThrow();
        List<String> categories = new ArrayList<>();

        for(Favorite favorite: account.getFavorites()){
            if(!categories.contains(favorite.getFavorite_book().getCategory())) categories.add(favorite.getFavorite_book().getCategory());
        }

        for(Sale history: account.getPurchase()){
            Book book = libraryService.getBookByCopyId(history.getCopy().getId());
            if(!categories.contains(book.getCategory())) categories.add(book.getCategory());
        }
      
        return libraryService.getRecommendedBooksByCategories(categories);
    }

    public List<BookFullInformationDTO> getFavoriteBooks(long id){
        Account account = accountRepository.findById(id).orElseThrow();
        List<BookFullInformationDTO> books = new ArrayList<>();

        for(Favorite favorite: account.getFavorites()){
            books.add(libraryService.getBookById(favorite.getFavorite_book().getId()));
        }

        return books;
    }

    public List<Copy> getShoppingCart(long userId){
        Account account = accountRepository.findById(userId).orElseThrow();
        List<Copy> copies = new ArrayList<>();
        for(ShoppingCart sp: account.getShoppingCart()){
            copies.add(sp.getAdded_copy());
        }
        return copies;
    }

    @Transactional
    public Favorite setFavoriteBook(long userId, String bookId){
        BookFullInformationDTO favoriteBook = libraryService.getBookById(Long.parseLong(bookId));
        Account account = accountRepository.findById(userId).orElseThrow();
        Favorite favorite = new Favorite(account, favoriteBook.book());

        return favoritesRepository.save(favorite);
    }

    @Transactional
    public void deleteFavoriteBook(long userId, String bookId){
        Favorite favorite = favoritesRepository.findByBookIdAndUserId(userId, Long.parseLong(bookId)).orElseThrow();
        favoritesRepository.delete(favorite);
    }

    @Transactional
    public ShoppingCart addCopyToShoppingCart(long userId, String copyId){
        Copy copy = libraryService.getCopyById(Long.parseLong(copyId));
        Account account = accountRepository.findById(userId).orElseThrow();
        ShoppingCart shoppingCart = new ShoppingCart(account, copy);

        return shoppingCartRepository.save(shoppingCart);
    }

    @Transactional
    public void deleteCopyInShoppingCart(long userId, String copyId){
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserIdAndCopyId(userId, Long.parseLong(copyId));
        shoppingCartRepository.delete(shoppingCart);
    }
}
