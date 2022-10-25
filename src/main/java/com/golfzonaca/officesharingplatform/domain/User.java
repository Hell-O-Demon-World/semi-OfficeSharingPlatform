package com.golfzonaca.officesharingplatform.domain;

import lombok.Data;

import java.util.concurrent.atomic.AtomicLong;

@Data
public class User {
    private long id;
    private String name;
    private String mail;
    private String pw;
    private long mileageId;
    private String phoneNumber;
    private String job;
    private String preferType;

    public User(String name, String mail, String pw, long mileageId) {
        this.name = name;
        this.mail = mail;
        this.pw = pw;
        this.mileageId = mileageId;
    }
}
