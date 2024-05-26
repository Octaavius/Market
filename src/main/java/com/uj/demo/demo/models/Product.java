package com.uj.demo.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    private String brand;

    private String model;

    private String color;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private String sizes;

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

    public Long getId() {
        return id;
    }

    public ProductType getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public Sex getSex() {
        return sex;
    }

    public String getSizes() {
        return sizes;
    }

    public double getPrice() {
        return price;
    }
}
