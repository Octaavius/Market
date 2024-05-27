package com.uj.demo.demo.services;

import com.uj.demo.demo.models.Session;
import com.uj.demo.demo.models.User;
import com.uj.demo.demo.repositories.SessionRepository;
import org.springframework.stereotype.Service;

import java.time.Clock;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void actualizeSession(Session session) {
        sessionRepository.updateLastLogin(session.getId(), Clock.systemDefaultZone().instant());
    }

    public Session create(User user) {
        Session session = new Session();
        session.setLastLogin(Clock.systemDefaultZone().instant());
        session.setUser(user);
        return sessionRepository.save(session);
    }

    public Session findByUser(Long userId) {
        return sessionRepository.findByUserId(userId);
    }
}