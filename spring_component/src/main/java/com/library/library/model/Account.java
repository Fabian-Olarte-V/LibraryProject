package com.library.library.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;


@Data
@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "buyer")
    @JsonIgnore
    private List<Sale> purchase = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<ShoppingCart> shoppingCart = new ArrayList<>();

    private String name;
    private String role;
    private String email;
    private String password;

    
    public Account(){
        
    }

    public Account(String name, String role, String email, String password) {
        this.name = name;
        this.role = role;
        this.email = email;
        this.password = password;
    }

}
