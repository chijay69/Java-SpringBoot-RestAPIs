package com.example.api.model.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

@Entity
@Data
public class RsaGrowth {
    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

//    @Column(name = "fund_id")
//    private int fundId;

    @Column(name = "gainLoss")
    private BigDecimal gainLoss;
}
