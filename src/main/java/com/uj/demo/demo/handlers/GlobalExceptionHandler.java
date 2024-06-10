package com.uj.demo.demo.handlers;

import com.uj.demo.demo.exceptions.UserAlreadyExistsException;
import com.uj.demo.demo.exceptions.UserNotExistsException;
import com.uj.demo.demo.exceptions.WrongLoginOrPasswordException;
import com.uj.demo.demo.exceptions.WrongPasswordFormatException;
import com.uj.demo.demo.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        logger.error("An error occurred", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
    }

    @ExceptionHandler(WrongLoginOrPasswordException.class)
    public ModelAndView handleWrongLoginOrPasswordException(WrongLoginOrPasswordException ex, Model model) {
        logger.error("Wrong login or password exception occurred", ex);
        model.addAttribute("user", new User());
        model.addAttribute("message", "Invalid username or password.");
        return new ModelAndView("login");
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ModelAndView handleUserAlreadyExistsException(UserAlreadyExistsException ex, Model model) {
        logger.error("User already exists exception occurred", ex);
        model.addAttribute("user", new User());
        model.addAttribute("message", "User already exists");
        return new ModelAndView("signup");
    }

    @ExceptionHandler(WrongPasswordFormatException.class)
    public ModelAndView handleUserAlreadyExistsException(WrongPasswordFormatException ex, Model model) {
        logger.error("Wrong password format exception occurred", ex);
        model.addAttribute("user", new User());
        model.addAttribute("message", "Password must contain at least 8 characters");
        return new ModelAndView("signup");
    }

    @ExceptionHandler({UserNotExistsException.class})
    public ModelAndView handleUserNotExistsException(UserNotExistsException ex, Model model) {
        logger.error("User not exists exception exception occurred", ex);
        model.addAttribute("user", new User());
        return new ModelAndView("login");
    }
}