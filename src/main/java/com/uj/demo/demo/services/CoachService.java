package com.uj.demo.demo.services;

import com.uj.demo.demo.models.Coach;
import com.uj.demo.demo.repositories.CoachRepository;
import com.uj.demo.demo.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachService {
    private final CoachRepository coachRepository;

    public CoachService(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    public List<Coach> getAllCoaches() {
        return coachRepository.findAll();
    }

    public Coach getCoachById(Long id) {
        return coachRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Coach not found with id: " + id));
    }

    public Coach addCoach(Coach coach) {
        return coachRepository.save(coach);
    }
}
