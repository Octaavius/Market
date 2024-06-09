package com.uj.demo.demo.handlers;

import com.uj.demo.demo.exceptions.UserAlreadyExistsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

public class UserAlreadyExistsHandler {
    private static final Logger logger = LogManager.getLogger(UserAlreadyExistsException.class);

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ModelAndView handleUserAlreadyExistsException(UserAlreadyExistsException ex, Model model) {
        logger.error("User already exists exception occurred", ex);
        model.addAttribute("message", "User already exists");
        return new ModelAndView("redirect:/signup");
    }
}
