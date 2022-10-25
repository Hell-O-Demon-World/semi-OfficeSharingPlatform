package com.golfzonaca.officesharingplatform.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
@Access(AccessType.PROPERTY)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false)
    private Long id;

    @Column(name = "LOCATION", length = 100, nullable = false)
    private String address;

    @Column(name = "POSTALCODE", length = 6, nullable = false)
    private int postalCode;

    public Address() {
    }

    public Address(Long id, String address, int postalCode) {
        this.id = id;
        this.address = address;
        this.postalCode = postalCode;
    }
}
