package com.example.api.controller;

import com.example.api.model.data.RsaGrowth;
import com.example.api.model.dto.RsaGrowthDTO;
import com.example.api.repo.RsaGrowthRepository;
import com.example.api.service.RsaGrowthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rsaGrowth")
public class RsaGrowthController {
    @Autowired
    private RsaGrowthRepository rsaGrowthRepository;

    @Autowired
    private RsaGrowthService rsaGrowthService;


    @Transactional
    @GetMapping("/{param}")
    public ResponseEntity<RsaGrowth> fetchMobileAppData(@PathVariable("param") String param) {
//        RsaGrowth mobileAppData = rsaGrowthRepository.fetchMobileAppData(param);
        RsaGrowth mobileAppData = rsaGrowthService.getRsaGrowth(param);
        return ResponseEntity.ok(mobileAppData);
    }
}

