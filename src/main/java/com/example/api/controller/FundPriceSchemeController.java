package com.example.api.controller;

import com.example.api.model.dto.SchemeDTO;
import com.example.api.service.FundPriceSchemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schemes")
public class FundPriceSchemeController {
    private final FundPriceSchemeService fundPriceService;

    @Autowired
    public FundPriceSchemeController(FundPriceSchemeService fundPriceService) {
        this.fundPriceService = fundPriceService;
    }

    @GetMapping("/fundPrice")
    public Page<SchemeDTO> getFundPrices(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return fundPriceService.getAllSchemes(pageable);
    }

    @GetMapping("/fundPrice/{id}")
    public ResponseEntity<SchemeDTO> getSchemeById(@PathVariable("id") Long id) {
        SchemeDTO scheme = fundPriceService.getSchemeById(id);
        return (scheme != null) ? ResponseEntity.ok(scheme) : ResponseEntity.notFound().build();
    }
}
