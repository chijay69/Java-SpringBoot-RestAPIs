package com.example.api.model.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="EMPLOYEE_IMAGES")
public class EmployeeImage {
    @Id
    @Column(name="REGISTRATION_CODE")
    private String registrationCode;
    @Column(name="PIN")
    private String pin;
    @Column(name="SIGNATURE_IMAGE")
    private String signatureImage;
}