package com.uj.demo.demo.services;

import com.uj.demo.demo.exceptions.UserNotExistsException;
import com.uj.demo.demo.handlers.GlobalExceptionHandler;
import com.uj.demo.demo.models.User;
import com.uj.demo.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    private static final Logger logger = LogManager.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        logger.debug("Getting all users");
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        logger.debug("Saving user {}", user.getLogin());
        User exsistingUser = userRepository.findByLogin(user.getLogin());
        if (exsistingUser != null) {
            logger.warn("User {} already exists", user.getLogin());
            return null;
        }
        return userRepository.save(user);
    }

    public User findUser(User user) {
        logger.debug("Looking for user {}", user.getLogin());
        User userFromDb = userRepository.findByLogin(user.getLogin());
        if (userFromDb == null) {
            logger.warn("User {} not found", user.getLogin());
            return null;
        }
        if (userFromDb.getPassword().equals(user.getPassword())) {
            logger.info("User {} found", user.getLogin());
            return userFromDb;
        } else {
            logger.warn("Password for user {} is not matching", user.getLogin());
            return null;
        }
    }
}