package com.example.api.service;

import com.example.api.model.dto.SchemeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FundPriceSchemeService {
    Page<SchemeDTO> getAllSchemes(Pageable pageable);
    SchemeDTO getSchemeById(Long id);
}
