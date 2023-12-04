package com.example.api.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
public class SchemeDTO {
    private Long schemeId;
    private String schemeName;
    private BigDecimal offerPrice;
}
