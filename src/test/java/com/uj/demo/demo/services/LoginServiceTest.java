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

public class LoginServiceTest {

    @Mock
    private Model model;

    @Mock
    private HttpSession session;

    @Mock
    private UserService userService;

    @InjectMocks
    private LoginService loginService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAuthorizeUser_ValidUser() throws NoSuchAlgorithmException {
        User user = new User();
        user.setPassword("password");
        User userFromDb = new User();
        when(userService.findUser(any(User.class))).thenReturn(userFromDb);

        String viewName = loginService.authorizeUser(user, session, userService);
        assertEquals("redirect:/", viewName);
        verify(session).setAttribute("user", userFromDb);
        verify(model, never()).addAttribute(eq("message"), anyString());
    }

    /*@Test
    public void testAuthorizeUser_InvalidUser() throws NoSuchAlgorithmException {
        User user = new User();
        user.setPassword("password");
        when(userService.findUser(any(User.class))).thenReturn(null);
        String viewName = loginService.authorizeUser(user, session, userService);
        assertEquals("login", viewName);
        verify(session, never()).setAttribute(eq("user"), any());
        verify(model).addAttribute("message", "Invalid username or password.");
    }*/

    @Test
    public void testUnauthorizeUser() {
        String viewName = loginService.unauthorizeUser(session);
        assertEquals("redirect:/", viewName);
        verify(session).invalidate();
    }
}
