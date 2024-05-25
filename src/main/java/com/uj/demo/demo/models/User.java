package com.uj.demo.demo.models;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private String login;
    @Getter String password;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private final List<Product> cart = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<Product> getCart() {
        return cart;
    }

    public User() {}
    public User(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }
}
