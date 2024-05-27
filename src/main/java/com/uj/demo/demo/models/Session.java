package com.uj.demo.demo.models;

import jakarta.persistence.*;

import java.time.Clock;
import java.time.Instant;

@Entity
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Instant lastLogin = Clock.systemDefaultZone().instant();

    public Session(Long id, User user, Instant lastLogin) {
        this.id = id;
        this.user = user;
        this.lastLogin = lastLogin;
    }

    public Session() {
    }

    public void setLastLogin(Instant lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Instant getLastLogin() {
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
