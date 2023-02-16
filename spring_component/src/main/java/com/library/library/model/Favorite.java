package com.library.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Favorite {

    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne
    @JsonIgnore
    private Account user;

    @OneToOne
    @JsonIgnore
    private Book favorite_book;


    public Favorite() {
    }

    public Favorite(Account user, Book favorite_book) {
        this.user = user;
        this.favorite_book = favorite_book;
    }
}
