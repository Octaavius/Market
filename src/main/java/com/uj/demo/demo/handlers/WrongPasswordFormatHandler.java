package com.uj.demo.demo.handlers;

import com.uj.demo.demo.exceptions.UserAlreadyExistsException;
import com.uj.demo.demo.exceptions.WrongPasswordFormatException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

public class WrongPasswordFormatHandler {
    private static final Logger logger = LogManager.getLogger(WrongPasswordFormatHandler.class);

    @ExceptionHandler(WrongPasswordFormatException.class)
    public ModelAndView handleUserAlreadyExistsException(WrongPasswordFormatException ex, Model model) {
        logger.error("Wrong password format exception occurred", ex);
        model.addAttribute("message", "Password must contain at least 8 characters");
        return new ModelAndView("redirect:/signup");
    }
}
