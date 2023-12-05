package com.example.api.service;

import com.example.api.model.data.CountryInfo;
import com.example.api.model.data.Location;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressRetrieverService {
    private final List<CountryInfo> countryInfoList;

    public AddressRetrieverService() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        // Assuming your JSON file is named data.json
        File jsonFile = new File("src/main/java/com/example/api/service/Data.json");
        countryInfoList = mapper.readValue(jsonFile, new TypeReference<>() {
        });
    }

    public Location getAddressByCountryAndState(String country, String state) {
        List<Location> matchingLocations = countryInfoList.stream()
                .filter(info -> country.equalsIgnoreCase(info.getCountry()))
                .flatMap(info -> info.getLocations().stream())
                .filter(location -> state == null || state.equalsIgnoreCase(location.getState()))
                .collect(Collectors.toList());

        Collections.reverse(matchingLocations); // Reverse the list

        if (!matchingLocations.isEmpty()) {
            return matchingLocations.get(0);
//            .getAddress()// Return the first matching address
        } else {
            throw new RuntimeException("Address not found for the given parameters.");
        }
    }

    // Example of usage
//    public static void main(String[] args) throws IOException {
//        AddressRetrieverService addressRetriever = new AddressRetrieverService();
//        String country = "Canada";
//        String state = "Abuja";
//        String address = addressRetriever.getAddressByCountryAndState(country, state);
//        System.out.println("Address: " + address);
//    }
}
