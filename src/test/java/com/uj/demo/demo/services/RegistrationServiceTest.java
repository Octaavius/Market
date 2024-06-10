package com.uj.demo.demo.services;

import com.uj.demo.demo.models.User;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RegistrationServiceTest {

    @Mock
    private Model model;

    @Mock
    private HttpSession session;

    @Mock
    private UserService userService;

    @InjectMocks
    private RegistrationService registrationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddNewUser_Success() throws NoSuchAlgorithmException {
        User user = new User();
        user.setPassword("password");
        User savedUser = new User();
        when(userService.saveUser(any(User.class))).thenReturn(savedUser);

        String viewName = registrationService.addNewUser(user, session, userService);

        assertEquals("redirect:/", viewName);
        verify(session).setAttribute("user", savedUser);
        verify(model, never()).addAttribute(eq("message"), anyString());
    }

    /*@Test
    public void testAddNewUser_UserExists() throws NoSuchAlgorithmException {
        User user = new User();
        user.setPassword("password");

        when(userService.saveUser(any(User.class))).thenReturn(null);

        String viewName = registrationService.addNewUser(user, session, userService);

        assertEquals("signup", viewName);
        verify(session, never()).setAttribute(eq("user"), any());
        verify(model).addAttribute("message", "Such username already exists.");
    }*/
}
