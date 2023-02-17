package com.example.usertest.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest {

    private String country;
    private String city;
    private String zipCode;
    private String streetName;

    public AddressRequest() {
    }

    public AddressRequest(String country, String city, String zipCode, String streetName) {
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
        this.streetName = streetName;
    }
}
