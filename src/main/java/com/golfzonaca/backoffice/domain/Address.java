package com.golfzonaca.backoffice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private long id;
    private String location;
    private String postalCode;

    public Address(String location, String postalCode) {
        this.location = location;
        this.postalCode = postalCode;
    }
}

