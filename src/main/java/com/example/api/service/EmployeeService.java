package com.example.api.service;

import com.example.api.model.data.EmployeeDto;
import com.example.api.model.data.Employees;

public interface EmployeeService {
    Boolean confirmEmployee(String employeePin);
    EmployeeDto findEmployee(String pin);
}
