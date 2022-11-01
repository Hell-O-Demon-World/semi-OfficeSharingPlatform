package com.golfzonaca.officesharingplatform.domain;

import lombok.Data;

@Data
public class Address {

    private Long id;

    private String address;

    private int postalCode;

    public Address() {
    }

    public Address(Long id, String address, int postalCode) {
        this.id = id;
        this.address = address;
        this.postalCode = postalCode;
    }
}
