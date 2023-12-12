package com.example.api.repo;


import com.example.api.model.data.EmployeeImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeImageRepository extends JpaRepository<EmployeeImage, String> {
}
