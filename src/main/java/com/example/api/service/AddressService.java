//package com.example.api.service;
//
//
//import com.example.api.model.data.Location;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class AddressService {
//
//    private final List<Location> locations;
//
//    public AddressService() throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        ClassPathResource resource = new ClassPathResource("data.json");
//
//        // Read JSON file into a List<Location>
//        this.locations = mapper.readValue(resource.getInputStream(), new TypeReference<List<Location>>() {});
//    }
//
//    public List<Location> getAddressesByCountry(String country) {
//        return locations.stream()
//                .filter(location -> location.getCountry().equalsIgnoreCase(country))
//                .collect(Collectors.toList());
//    }
//
//    public List<Location> getAddressesByState(String state) {
//        return locations.stream()
//                .filter(location -> location.getState().equalsIgnoreCase(state))
//                .collect(Collectors.toList());
//    }
//
//    public Location getAddressByCountryAndState(String country, String state) {
//        return locations.stream()
//                .filter(location -> location.getCountry().equalsIgnoreCase(country)
//                        && location.getState().equalsIgnoreCase(state))
//                .findFirst()
//                .orElse(null);
//    }
//}
