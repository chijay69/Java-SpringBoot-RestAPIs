package com.example.api.model.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "SCHEMES")
public class Schemes {
    @Id
    @Column(name = "SCHEME_ID", precision = 10, scale = 0, nullable = false)
    private Long schemeId;
    @Column(name = "SCHEME_NAME")
    private String schemeName;
    @Column(name = "OFFER_PRICE")
    private BigDecimal offerPrice;
}
