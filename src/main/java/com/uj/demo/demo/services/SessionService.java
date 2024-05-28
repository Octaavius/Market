package com.uj.demo.demo.services;

import com.uj.demo.demo.models.Session;
import com.uj.demo.demo.models.User;
import com.uj.demo.demo.repositories.CustomSessionRepository;
import org.springframework.stereotype.Service;

import java.time.Clock;

@Service
public class SessionService {
    private final CustomSessionRepository customSessionRepository;

    public SessionService(CustomSessionRepository customSessionRepository) {
        this.customSessionRepository = customSessionRepository;
    }

    public void actualizeSession(Session session) {
        customSessionRepository.updateLastLogin(session.getId(), Clock.systemDefaultZone().instant());
    }

    public Session create(User user) {
        Session session = new Session();
        session.setLastLogin(Clock.systemDefaultZone().instant());
        session.setUser(user);
        return customSessionRepository.save(session);
    }

    public Session findByUser(Long userId) {
        return customSessionRepository.findByUserId(userId);
    }
}