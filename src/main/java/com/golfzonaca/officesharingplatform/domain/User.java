package com.golfzonaca.officesharingplatform.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

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

    private String role; // 권한


    public User(String name, String mail, String pw, String phoneNumber, String job, String preferType) {
        this.name = name;
        this.mail = mail;
        this.pw = pw;
        this.phoneNumber = phoneNumber;
        this.job = job;
        this.preferType = preferType;
    }
}
