package com.uj.demo.demo.repositories;

import com.uj.demo.demo.models.Product;
import com.uj.demo.demo.models.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    <S extends Product> S save(@Nullable S product);
    List<Product> findByModel(String name);
}
