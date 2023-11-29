package com.example.api.model.data;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CountryInfo {
    private String country;
    private String embassy;
    private List<Location> locations;
}