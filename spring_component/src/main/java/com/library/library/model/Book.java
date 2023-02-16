package com.library.library.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int isbn;
    private int numPages;
    private String editorial;
    private String category;
    private String description;
    private String language;
    private int score;

    @OneToOne(mappedBy = "favorite_book", cascade = CascadeType.ALL)
    @JsonIgnore
    private Favorite favorites;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Copy> copies = new ArrayList<>();

    @ManyToMany
    @JsonIgnore
    private List<Author> authors = new ArrayList<>();

    public Book(){

    }

    public Book(String name, int isbn, int numPages, String editorial, String category, String description, String language, int score) {
        this.name = name;
        this.isbn = isbn;
        this.numPages = numPages;
        this.editorial = editorial;
        this.category = category;
        this.description = description;
        this.language = language;
        this.score = score;
    }
}
