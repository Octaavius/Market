package com.uj.demo.demo.models;

import jakarta.persistence.*;

import java.time.Clock;

@Entity
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Clock lastLogin;

    public Session(Long id, User user, Clock lastLogin) {
        this.id = id;
        this.user = user;
        this.lastLogin = lastLogin;
    }

    public Session() {
    }

    public void setLastLogin(Clock lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Clock getLastLogin() {
        return lastLogin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
