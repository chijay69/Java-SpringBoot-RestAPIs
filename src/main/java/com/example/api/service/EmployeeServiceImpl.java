package com.example.api.service;



import com.example.api.model.data.Employees;
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

    @Override
    public Employees findEmployee(String pin) {
        return employeeRepo.findEmployeesByPin(pin)
                .orElseThrow(() -> new RuntimeException("Employee does not exist"));
    }
}
