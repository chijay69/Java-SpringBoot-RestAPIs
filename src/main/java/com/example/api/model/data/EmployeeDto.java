package com.example.api.model.data;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
    private String registrationCode;
    private String pin;
    private String firstname;
    private String othernames;
    private String gender;
}
