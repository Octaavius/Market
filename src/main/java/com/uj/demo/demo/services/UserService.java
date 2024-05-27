package com.uj.demo.demo.services;

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

    public User saveUser(User user) {
        User exsistingUser = userRepository.findByLogin(user.getLogin());
        if(exsistingUser != null){
            return null;
        }
        return userRepository.save(user);
    }

    public User findUser(User user) {
        User userFromDb = userRepository.findByLogin(user.getLogin());
        if(userFromDb == null) return null;
        if(userFromDb.getPassword().equals(user.getPassword())){
            return userFromDb;
        } else {
            return null;
        }
    }
}