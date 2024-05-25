package com.uj.demo.demo.repositories;

import com.uj.demo.demo.models.Product;
import com.uj.demo.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

public interface ProductRepository extends JpaRepository<Product, Long> {
    <S extends Product> S save(@Nullable S product);

    Product findByModel(String model);
}
