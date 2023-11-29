package com.example.api.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString()
public class Location {
    private String address;
    @JsonProperty("city/district")
    private String cityDistrict;
    private String state;
    private String tel;
    private String fax;
    private String email;
    @JsonProperty("addressedTo")
    private String addressedTo;
}
