package com.uj.demo.demo.services;

import com.uj.demo.demo.models.Coach;
import com.uj.demo.demo.models.Employee;
import com.uj.demo.demo.repositories.CoachRepository;
import com.uj.demo.demo.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getCoachById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Coach not found with id: " + id));
    }

    public Employee addCoach(Employee coach) {
        return employeeRepository.save(coach);
    }
}
