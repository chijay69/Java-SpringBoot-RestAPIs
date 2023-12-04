package com.example.api.service;

import com.example.api.model.data.Scheme;
import com.example.api.model.dto.SchemeDTO;
import com.example.api.repo.FundPriceSchemeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FundPriceSchemeServiceImpl implements FundPriceSchemeService {
    private final FundPriceSchemeRepo fundPriceSchemeRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public FundPriceSchemeServiceImpl(FundPriceSchemeRepo fundPriceSchemeRepo, ModelMapper modelMapper) {
        this.fundPriceSchemeRepo = fundPriceSchemeRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<SchemeDTO> getAllSchemes(Pageable pageable) {
        Page<Scheme> schemesPage = fundPriceSchemeRepo.findAll(pageable);
        return schemesPage.map(scheme -> modelMapper.map(scheme, SchemeDTO.class));
    }

    @Override
    public SchemeDTO getSchemeById(Long id) {
        Optional<Scheme> scheme = fundPriceSchemeRepo.findById(id);
        return scheme.map(value -> modelMapper.map(value, SchemeDTO.class)).orElse(null);
    }
}
