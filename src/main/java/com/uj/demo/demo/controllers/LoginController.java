package com.uj.demo.demo.controllers;

import com.uj.demo.demo.models.Order;
import com.uj.demo.demo.repositories.OrderRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @GetMapping("/login")
    public String login() {return "login";}


}

