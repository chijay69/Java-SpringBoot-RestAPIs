package com.example.api.repo;

import com.example.api.model.data.Scheme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundPriceSchemeRepo extends JpaRepository<Scheme, Long> {
    // No need to declare findAll(), it's already provided by JpaRepository
    Page<Scheme> findAll(Pageable pageable);
}
