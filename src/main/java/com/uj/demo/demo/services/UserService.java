package com.uj.demo.demo.services;

import com.uj.demo.demo.models.User;
import com.uj.demo.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        User exsistingUser = userRepository.findByLogin(user.getLogin());
        if(exsistingUser != null){
            return null;
        }
        return userRepository.save(user);
    }
}