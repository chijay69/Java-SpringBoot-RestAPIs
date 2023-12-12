package com.example.api.service;

import com.example.api.exceptions.UserException;
import com.example.api.mail.MailSenderService;
import com.example.api.mail.SendMailDto;
import com.example.api.model.data.Employees;
import com.example.api.model.dto.request.EmailDto;
import com.example.api.repo.EmployeeRepo;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.api.utils.ConstantUtils.*;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepo employeeRepo;
    private final MailSenderService mailSenderService;
    private final JwtTokenService jwtTokenService;
    @Value("${spring.mail.username}")
    private String from;

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
    public Employees updateEmail(EmailDto updateEmailDto) {
        if (isBlank(updateEmailDto.getPin())) throw new UserException(INVALID_PIN);
        Employees employee = findEmployee(updateEmailDto.getPin());
        if (!emailIsValid(updateEmailDto.getEmail())) throw new UserException(INVALID_EMAIL);
        Optional<Employees> employeeByEmail = employeeRepo.findEmployeesByEmail(updateEmailDto.getEmail());
        if (employeeByEmail.isPresent()) throw new UserException(EMAIL_ALREADY_EXIST);
        employee.setEmail(updateEmailDto.getEmail());
        return employeeRepo.save(employee);
    }
    @Override
    public String authenticateUser(EmailDto emailDto){
        String token = jwtTokenService.generateToken(emailDto.getEmail(), emailDto.getPin());
        String link = "http://localhost:3000/start?token=" + token;
        SendMailDto sendMailDto = getSendMailDto(emailDto.getEmail(), link);
        mailSenderService.sendMail(sendMailDto);
        return "Check your email for confirmation link";
    }

    @Override
    public String confirmUser(String token) {
      if (!jwtTokenService.isValidUrl(token)) throw new RuntimeException("Sorry, this url has been or expired");
      String pin = jwtTokenService.extractPinFromToken(token);
      String email = jwtTokenService.extractEmailFromToken(token);
      Employees employee = employeeRepo.findEmployeesByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
      if (employee.getPin().equals(pin)){
          jwtTokenService.invalidatePreviousNonce(email);
          return "User confirmed successfully";
      }
      else throw new RuntimeException("User not found");
    }

    private SendMailDto getSendMailDto(String email, String link) {
        SendMailDto sendMailDto = new SendMailDto();
        sendMailDto.setFrom(from);
        sendMailDto.setTo(email);
        sendMailDto.setSubject("Confirmation Link");
        String message = "Dear Valued Customer,\n" +
                " \n" +
                "Kindly click on the link below to proceed with your fund transfer. " +
                "The link expires in one hour\n" +
                 link + " \n" +
                "Thank you for choosing CrusaderSterling Pensions as your preferred PFA.\n";
        sendMailDto.setMessage(message);
        return sendMailDto;
    }

    private boolean emailIsValid(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    private boolean isBlank(String pin) {
        return StringUtils.isBlank(pin);
    }

}