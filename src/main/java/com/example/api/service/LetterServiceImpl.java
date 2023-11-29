package com.example.api.service;

import com.example.api.model.data.Employees;
import com.example.api.model.data.Location;
import com.example.api.model.dto.request.GenerateLetterRequest;
import com.example.api.pdf.LetterGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LetterServiceImpl implements LetterService{
    private final EmployeeService employeeService;
    private final LetterGenerator letterGenerator;
    private final AddressRetrieverService addressRetrieverService;

    @Override
    public String generateEmployeeLetter(GenerateLetterRequest request) {
        Employees employee = employeeService.findEmployee(request.getPin());
        String employeePin = employee.getPin();
        String employeeFullName = employee.getFirstname() + " " + employee.getSurname();
        String outputPath = "src/main/resources/pdf/template.pdf";
        Location location = addressRetrieverService.getAddressByCountryAndState(request.getCountry(), request.getState());
        log.info("location ---> " + location);
        letterGenerator.generateLetter(outputPath, employeeFullName, employeePin, location, request.getCountry());
        return "Pdf Generated Successfully";
    }
}
