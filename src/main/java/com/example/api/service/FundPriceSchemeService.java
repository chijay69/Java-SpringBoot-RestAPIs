package com.example.api.service;

import com.example.api.model.data.Schemes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FundPriceSchemeService {
    List<Schemes> getFundPrice();
}
