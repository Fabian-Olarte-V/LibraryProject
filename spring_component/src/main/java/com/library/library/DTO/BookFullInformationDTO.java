package com.library.library.DTO;

import java.util.List;
import com.library.library.model.Author;
import com.library.library.model.Book;
import com.library.library.model.Copy;

public record BookFullInformationDTO(Book book, List<Copy> copies, List<Author> authors) { }
