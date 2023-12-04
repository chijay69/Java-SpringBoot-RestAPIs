package com.example.api.service;

import com.example.api.model.data.RsaGrowth;
import com.example.api.repo.RsaGrowthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RsaGrowthServiceImpl implements RsaGrowthService{

    @Autowired
    private RsaGrowthRepository rsaGrowthRepository;
    @Override
    public RsaGrowth getRsaGrowth(String param) {
        return rsaGrowthRepository.fetchMobileAppData(param);
    }
}
