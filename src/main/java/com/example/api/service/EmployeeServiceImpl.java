package com.example.api.service;


import com.example.api.repo.EmployeeRepo;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepo employeeRepo;

    @Override
    public Boolean confirmEmployee(String employeePin) {
        if (StringUtils.isBlank(employeePin)) throw new RuntimeException("Invalid pin");
        return employeeRepo.existsByPin(employeePin);
    }
}
