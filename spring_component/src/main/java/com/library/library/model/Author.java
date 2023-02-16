package com.library.library.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Author {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(mappedBy = "authors", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Book> books = new ArrayList<>(); 

    private String name;
    private String nationality;
    private String description;


    public Author(){
        
    }

    public Author(String name, String nationality, String description){
        this.name = name;
        this.nationality = nationality;
        this.description = description;
    }
}
