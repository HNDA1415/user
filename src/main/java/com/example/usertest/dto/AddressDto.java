package com.example.usertest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
    private Integer id;
    private String country;
    private String city;
    private String zipCode;
    private String streetName;

    public AddressDto() {
    }

    public AddressDto(Integer id, String country, String city, String zipCode, String streetName) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
        this.streetName = streetName;
    }
}
