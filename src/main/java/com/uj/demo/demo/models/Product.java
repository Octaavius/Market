package com.uj.demo.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
public class Product {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Enumerated(EnumType.STRING)
    private ProductType type;

    @Getter
    private String brand;

    @Getter
    private String model;

    @Getter
    private String color;

    @Getter
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Getter
    private String sizes;

    @Getter
    private double price;

    protected Product() {}

    public Product(Long id, ProductType type, String brand, String model, String color, Sex sex, String sizes, double price) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.sex = sex;
        this.sizes = sizes;
        this.price = price;
    }
}
