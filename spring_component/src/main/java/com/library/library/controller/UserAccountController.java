package com.library.library.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.library.library.DTO.BookFullInformationDTO;
import com.library.library.model.Account;
import com.library.library.model.Copy;
import com.library.library.model.Favorite;
import com.library.library.model.ShoppingCart;
import com.library.library.services.UserService;
import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/user")
public class UserAccountController {

    @Autowired
    UserService userService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/account")
    public Account getAccountByEmail(HttpServletRequest request){
        return userService.getAccountByEmail(request.getParameter("userEmail"));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("{id}/recommended-books")
    public List<BookFullInformationDTO> getRecommendedBooks(@PathVariable("id") long userId){
        return userService.getRecommendedBooksByUserId(userId);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}/favorite-books") 
    public List<BookFullInformationDTO  > getFavoriteBooks(@PathVariable("id") long id){
        return userService.getFavoriteBooks(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/{id}/add-favorite") 
    public Favorite setFavoriteBook(@PathVariable("id") long userId, @RequestBody String bookId){  
        return userService.setFavoriteBook(userId, bookId);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}/remove-favorite") 
    public void removeFavoriteBook(@PathVariable("id") long userId, HttpServletRequest request){
        userService.deleteFavoriteBook(userId, request.getParameter("bookId"));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}/shoppingCart") 
    public List<Copy> getShoppingCart(@PathVariable("id") long userId){
        return userService.getShoppingCart(userId);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/{id}/add-shoppingCart") 
    public ShoppingCart addShoppingCart(@PathVariable("id") long userId, @RequestBody String copyId){
        return userService.addCopyToShoppingCart(userId, copyId);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}/remove-shoppingCart") 
    public void removeShoppingCart(@PathVariable("id") long userId, HttpServletRequest request){
        userService.deleteCopyInShoppingCart(userId, request.getParameter("copyId"));
    }
}

