package com.uj.demo.demo.models;

import jakarta.persistence.Entity;
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
    private ProductType type;
    @Getter
    private String brand;
    @Getter
    private String model;
    @Getter
    private String color;
    @Getter
    private Sex sex;
    @Getter
    private String sizes;
    @Getter
    private double price;

    Product(){}

    public Product(Long id, ProductType type1, String brand, String model, String color, Sex sex, String sizes, double price) {
        this.id = id;
        this.sex = sex;
        type = type1;
        this.brand = brand;
        this.model = model;
        this.color = color;
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

