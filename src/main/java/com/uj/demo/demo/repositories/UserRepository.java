package com.uj.demo.demo.repositories;

import com.uj.demo.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    <S extends User> S save(@Nullable S user);

    <S extends User> S findByLogin(String Login);
}
