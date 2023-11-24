package com.example.api.repo;

import com.example.api.model.data.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    boolean existsByPin(String pin);
}
