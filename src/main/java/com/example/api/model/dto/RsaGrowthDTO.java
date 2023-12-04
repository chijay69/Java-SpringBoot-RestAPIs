package com.example.api.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class RsaGrowthDTO {
    private String email;
    private String firstName;
    private int fundId;
    private BigDecimal gainLoss;
}
