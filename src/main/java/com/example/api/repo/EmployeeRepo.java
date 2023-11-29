package com.example.api.repo;

import com.example.api.model.data.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employees, Long> {
    boolean existsByPin(String pin);
    Optional<Employees> findEmployeesByPin(String pin);
    Optional<Employees> findEmployeesByEmail(String email);
}
