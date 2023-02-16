package com.library.library.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.library.library.DTO.BookFullInformationDTO;
import com.library.library.model.Book;
import com.library.library.model.Copy;
import com.library.library.repository.AuthorRepository;
import com.library.library.repository.BookRepository;
import com.library.library.repository.CopyRepository;

@Service
public class LibraryService {

    @Autowired
    UserService userService;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired 
    CopyRepository copyRepository;


    public List<BookFullInformationDTO> getBooks(){    
        List<BookFullInformationDTO> bookList = new ArrayList<>();
        for(Book book: (List<Book>) bookRepository.findAll()){
            bookList.add(new BookFullInformationDTO(book, book.getCopies(), book.getAuthors()));
        }

        return bookList;
    }

    public BookFullInformationDTO getBookById(long id){
        Book book = bookRepository.findById(id).orElseThrow();
        return new BookFullInformationDTO(book, book.getCopies(), book.getAuthors());
    }

    public List<Copy> getCopiesByBookId(long id){
        Book book = bookRepository.findById(id).orElseThrow();
        return book.getCopies();
    }

    public Copy getCopyById(long id){
        return copyRepository.findById(id).orElseThrow();
    }

    public List<BookFullInformationDTO> getRecommendedBooksByCategories(List<String> categories){
        List<BookFullInformationDTO> books = new ArrayList<>();
        for(Book book: bookRepository.findRecommendedBooks(categories)){
            books.add(new BookFullInformationDTO(book, book.getCopies(), book.getAuthors()));
        }

        return books;
    }

    public Book getBookByCopyId(Long copyId){
        Copy copy = copyRepository.findById(copyId).orElseThrow();
        return copy.getBook();
    }


}
