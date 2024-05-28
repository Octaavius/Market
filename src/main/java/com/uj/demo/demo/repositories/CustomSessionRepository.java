package com.uj.demo.demo.repositories;

import com.uj.demo.demo.models.Session;
import java.time.Instant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

public interface CustomSessionRepository extends JpaRepository<Session, Long> {
    <S extends Session> S save(@Nullable S session);

    Session findByUserId(Long userId);

    @Modifying
    @Query("UPDATE Session s SET s.lastLogin = :lastLogin WHERE s.id = :sessionId")
    void updateLastLogin(@Param("sessionId")Long id,@Param("lastLogin") Instant lastLogin);
}
