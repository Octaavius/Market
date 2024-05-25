package com.uj.demo.demo.repositories;

import com.uj.demo.demo.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    <S extends Employee> S save(@Nullable S coach);
}