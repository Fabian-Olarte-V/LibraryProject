package com.library.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Copy {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "copy", cascade = CascadeType.ALL)
    @JsonIgnore
    private Sale sale;

    @ManyToOne
    @JsonIgnore
    private Book book;

    @OneToOne(mappedBy = "added_copy", cascade = CascadeType.ALL)
    @JsonIgnore
    private ShoppingCart addedCopy;
    
    private int serial;
    private String status;
    private int price;

    public Copy(){

    }

    public Copy(int serial, String status, int price){
        this.serial = serial;
        this.status = status;
        this.price = price;
    }

    public Copy(int serial, String status, int price, Book book) {
        this.serial = serial;
        this.status = status;
        this.price = price;
        this.book = book;
    }
}
