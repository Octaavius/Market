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

    public User(Long id, String name, String login, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Getter
    private String name;
    @Getter
    private String login;
    @Getter String password;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private final List<Product> cart = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
}
