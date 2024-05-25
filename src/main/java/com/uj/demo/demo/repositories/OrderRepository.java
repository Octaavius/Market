package com.uj.demo.demo.repositories;

import com.uj.demo.demo.models.Order;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    <S extends Order> S save(@Nullable S order);
}
