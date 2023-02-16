package com.library.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class ShoppingCart {
    
    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    @JsonIgnore
    private Account user;

    @OneToOne
    @JsonIgnore
    private Copy added_copy;


    public ShoppingCart() {

    }

    public ShoppingCart(Account user, Copy added_copy) {
        this.user = user;
        this.added_copy = added_copy;
    }
}
