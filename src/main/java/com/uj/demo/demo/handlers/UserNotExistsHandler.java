package com.uj.demo.demo.handlers;

import com.uj.demo.demo.exceptions.UserNotExistsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

public class UserNotExistsHandler {
    private static final Logger logger = LogManager.getLogger(UserNotExistsException.class);

    @ExceptionHandler(UserNotExistsException.class)
    public ModelAndView handleUserNotExistsException(UserNotExistsException ex) {
        logger.error("User not exists exception exception occurred", ex);
        return new ModelAndView("redirect:/login");
    }
}
