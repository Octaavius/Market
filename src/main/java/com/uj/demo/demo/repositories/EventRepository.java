package com.uj.demo.demo.repositories;

import com.uj.demo.demo.models.Event;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByCoachId(Long coachId);

    List<Event> findByClubId(Long clubId);

    <S extends Event> S save(@Nullable S coach);
}
