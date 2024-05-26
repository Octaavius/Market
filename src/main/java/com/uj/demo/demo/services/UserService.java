package com.uj.demo.demo.services;

import com.uj.demo.demo.models.Employee;
import com.uj.demo.demo.models.User;
import com.uj.demo.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {return userRepository.save(user);}

    public boolean userExists(User user) {
        User userFromDb = userRepository.findByLogin(user.getLogin());
        if(userFromDb == null) return false;
        return userFromDb.getPassword().equals(user.getPassword());
    }
}