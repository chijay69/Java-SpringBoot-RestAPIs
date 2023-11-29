package com.example.api.service;



import com.example.api.exceptions.UserException;
import com.example.api.model.data.Employees;
import com.example.api.model.dto.request.UpdateEmailDto;
import com.example.api.repo.EmployeeRepo;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.api.utils.ConstantUtils.*;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepo employeeRepo;

    @Override
    public Boolean confirmEmployee(String employeePin) {
        if (isBlank(employeePin)) throw new UserException(INVALID_PIN);
        return employeeRepo.existsByPin(employeePin);
    }

    @Override
    public Employees findEmployee(String pin) {
        return employeeRepo.findEmployeesByPin(pin)
                .orElseThrow(() -> new UserException(USER_DOES_NOT_EXIST));
    }

    @Override
    public Employees updateEmail(UpdateEmailDto updateEmailDto) {
        if (isBlank(updateEmailDto.getPin())) throw new UserException(INVALID_PIN);
        Employees employee = findEmployee(updateEmailDto.getPin());
        if (!emailIsValid(updateEmailDto.getEmail())) throw new UserException(INVALID_EMAIL);
        Optional<Employees> employeeByEmail = employeeRepo.findEmployeesByEmail(updateEmailDto.getEmail());
        if (employeeByEmail.isPresent()) throw new UserException(EMAIL_ALREADY_EXIST);
        employee.setEmail(updateEmailDto.getEmail());
        return employeeRepo.save(employee);
    }

    private boolean emailIsValid(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    private boolean isBlank(String pin) {
        return StringUtils.isBlank(pin);
    }

}