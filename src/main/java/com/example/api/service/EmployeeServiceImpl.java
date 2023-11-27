package com.example.api.service;


import com.example.api.model.data.EmployeeDto;
import com.example.api.model.data.Employees;
import com.example.api.repo.EmployeeRepo;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepo employeeRepo;
    private final ModelMapper modelMapper;

    @Override
    public Boolean confirmEmployee(String employeePin) {
        if (StringUtils.isBlank(employeePin)) throw new RuntimeException("Invalid pin");
        return employeeRepo.existsByPin(employeePin);
    }

    @Override
    public EmployeeDto findEmployee(String pin) {
        Employees employee = employeeRepo.findEmployeesByPin(pin)
                .orElseThrow(() -> new RuntimeException("Employee does not exist"));
        return modelMapper.map(employee, EmployeeDto.class);
    }
}
