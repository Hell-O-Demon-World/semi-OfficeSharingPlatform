package com.golfzonaca.officesharingplatform.domain;

import com.golfzonaca.officesharingplatform.web.auth.form.prefertype.PreferType;
import lombok.Data;

import java.util.List;

@Data
public class User {
    private long id;
    private String name;
    private String mail;
    private String pw;
    private long mileageId;
    private String phoneNumber;
    private String job;
    private List<PreferType> preferType;

    private String authority;

    public User(String name, String mail, String pw, String phoneNumber, String job, List<PreferType> preferType) {
        this.name = name;
        this.mail = mail;
        this.pw = pw;
        this.phoneNumber = phoneNumber;
        this.job = job;
        this.preferType = preferType;
    }
}
