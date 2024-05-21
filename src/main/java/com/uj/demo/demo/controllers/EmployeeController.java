package com.uj.demo.demo.controllers;

import com.uj.demo.demo.models.Coach;
import com.uj.demo.demo.models.Employee;
import com.uj.demo.demo.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployyes() {
        return employeeService.getAllEmployees();
    }
}
