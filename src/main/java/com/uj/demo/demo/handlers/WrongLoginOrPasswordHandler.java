package com.uj.demo.demo.handlers;

import com.uj.demo.demo.exceptions.UserNotExistsException;
import com.uj.demo.demo.exceptions.WrongLoginOrPasswordException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

public class WrongLoginOrPasswordHandler {
    private static final Logger logger = LogManager.getLogger(WrongLoginOrPasswordHandler.class);

    @ExceptionHandler(WrongLoginOrPasswordException.class)
    public ModelAndView handleWrongLoginOrPasswordException(WrongLoginOrPasswordException ex, Model model) {
        logger.error("Wrong login or password exception occurred", ex);
        model.addAttribute("message", "Invalid username or password.");
        return new ModelAndView("redirect:/login");
    }
}
