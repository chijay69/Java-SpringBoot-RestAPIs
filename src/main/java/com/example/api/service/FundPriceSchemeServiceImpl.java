package com.example.api.service;

import com.example.api.model.data.Schemes;
import com.example.api.repo.FundPriceSchemeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FundPriceSchemeServiceImpl implements FundPriceSchemeService {

    private final FundPriceSchemeRepo fundPriceSchemeRepo;
    @Override
    public List<Schemes> getFundPrice() {
        return fundPriceSchemeRepo.findAll();
    }
}
