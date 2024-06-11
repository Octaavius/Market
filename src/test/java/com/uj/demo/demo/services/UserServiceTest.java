package com.uj.demo.demo.services;

import static org.junit.jupiter.api.Assertions.*;
import com.uj.demo.demo.models.User;
import com.uj.demo.demo.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.when;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveNewUser(){
        User user = new User(1L, "user", "user", "user");
        when(userRepository.findByLogin(user.getLogin())).thenReturn(null);
        when(userRepository.save(user)).thenReturn(user);
        User result = userService.saveUser(user);
        assertEquals(user, result);
    }

    @Test
    public void saveExistingUser(){
        User user = new User(1L, "user", "user", "user");
        when(userRepository.findByLogin(user.getLogin())).thenReturn(user);
        User result = userService.saveUser(user);
        assertEquals(null, result);
    }

    @Test
    public void findNonExistingUser(){
        User user = new User(1L, "user", "user", "user");
        when(userRepository.findByLogin(user.getLogin())).thenReturn(null);
        User result = userService.findUser(user);
        assertEquals(null, result);
    }

    @Test
    public void findExistingUserWithWrongPassword(){
        User correctUser = new User(1L, "user", "user", "user");
        User wrongUser = new User(1L, "user", "user", "1");
        when(userRepository.findByLogin(correctUser.getLogin())).thenReturn(correctUser);
        User result = userService.findUser(wrongUser);
        assertEquals(null, result);
    }

    @Test
    public void findExistingUser(){
        User user = new User(1L, "user", "user", "user");
        when(userRepository.findByLogin(user.getLogin())).thenReturn(user);
        User result = userService.findUser(user);
        assertEquals(user, result);
    }

    @Test
    public void findAllUsers(){
        User user1 = new User(1L, "user1", "user1", "user1");
        User user2 = new User(2L, "user2", "user2", "user2");
        when(userRepository.findAll()).thenReturn(List.of(user1, user2));
        List<User> result = userService.getAllUsers();
        assertEquals(List.of(user1, user2), result);
    }
}
