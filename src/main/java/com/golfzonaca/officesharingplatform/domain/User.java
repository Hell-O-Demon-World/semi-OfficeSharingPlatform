package com.golfzonaca.officesharingplatform.domain;

import lombok.Data;

import java.util.concurrent.atomic.AtomicLong;

@Data
public class User {
    private AtomicLong id;
    private String name;
    private String mail;
    private String pw;
    private AtomicLong mileageId;
    private String phoneNumber;
    private String job;
    private String preferType;

    public User(AtomicLong id, String name, String mail, String pw, AtomicLong mileageId) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.pw = pw;
        this.mileageId = mileageId;
    }
}
