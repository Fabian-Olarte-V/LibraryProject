package com.library.library.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.library.library.DTO.BookFullInformationDTO;
import com.library.library.model.Copy;
import com.library.library.services.LibraryService;


@RestController
@RequestMapping("/book")
public class LibraryController {

    @Autowired
    LibraryService libraryService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/list")
    public List<BookFullInformationDTO> getBookList(){
        return libraryService.getBooks();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/view/{id}")
    public BookFullInformationDTO getBookById(@PathVariable("id") long id){
        return libraryService.getBookById(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}/copies")
    public List<Copy> getCopiesByBookId(@PathVariable("id") long id){
        return libraryService.getCopiesByBookId(id);
    }
}



    

