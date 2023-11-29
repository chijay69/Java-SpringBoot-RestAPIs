package com.example.api.model.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employees {
    @Id
    private String pin;
    private String firstname;
    private String surname;
    private String email;
}
