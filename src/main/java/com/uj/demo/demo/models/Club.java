package com.uj.demo.demo.models;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Club {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private String name;
    @Getter
    private String address;
    @Getter
    private String openingHours;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final List<Event> events = new ArrayList<>();

    public Club() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public Club(Long id, String name, String address, String openingHours) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.openingHours = openingHours;
    }
}
