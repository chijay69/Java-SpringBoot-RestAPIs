package com.example.api.service;

import com.example.api.mail.MailSenderService;
import com.example.api.mail.SendMailDto;
import com.example.api.model.data.Employees;
import com.example.api.model.data.Location;
import com.example.api.model.dto.request.GenerateLetterRequest;
import com.example.api.pdf.LetterGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LetterServiceImpl implements LetterService{
    private final EmployeeService employeeService;
    private final MailSenderService mailSenderService;
    private final LetterGenerator letterGenerator;
    private final AddressRetrieverService addressRetrieverService;
    @Value("${spring.mail.username}")
    private String from;

    @Override
    public String generateEmployeeLetter(GenerateLetterRequest request) {
        Employees employee = employeeService.findEmployee(request.getPin());
        String employeePin = employee.getPin();
        String employeeFullName = employee.getFirstname() + " " + employee.getSurname();
        String outputPath = "src/main/resources/pdf/template.pdf";
        Location location = addressRetrieverService.getAddressByCountryAndState(request.getCountry(), request.getState());
        letterGenerator.generateLetter(outputPath, employeeFullName, employeePin, location, request.getCountry());
        SendMailDto sendMailDto = getSendMailDto(employee);
        mailSenderService.sendMailWithAttachment(sendMailDto, outputPath);
        return "Pdf Generated Successfully";
    }

    private SendMailDto getSendMailDto(Employees employee) {
        SendMailDto sendMailDto = new SendMailDto();
        sendMailDto.setFrom(from);
        sendMailDto.setTo(employee.getEmail());
        sendMailDto.setSubject("Introduction Letter");
        String message = """
                Dear Valued Customer,
                \s
                Kindly find attached your Introduction Letter to the Embassy.
                \s
                Thank you for choosing CrusaderSterling Pensions as your preferred PFA.
                """;
        sendMailDto.setMessage(message);
        return sendMailDto;
    }
}
