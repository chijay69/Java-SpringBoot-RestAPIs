package com.example.api.repo;

import com.example.api.model.data.Schemes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FundPriceSchemeRepo extends JpaRepository<Schemes, Long> {

    List<Schemes> findAll();
}
