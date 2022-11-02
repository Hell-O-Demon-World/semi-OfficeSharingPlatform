package com.golfzonaca.officesharingplatform.domain;

import com.golfzonaca.officesharingplatform.web.auth.form.prefertype.PreferType;
import lombok.Data;

import java.util.List;

@Data
public class User {
    private long id;
    private String userName;
    private String userMail;
    private String userPw;
    private long mileageId;
    private String userTel;
    private String userJob;
    private List<PreferType> userPlace;

    public User(String userName, String userMail, String userPw, String userTel, String userJob, List<PreferType> userPlace) {
        this.userName = userName;
        this.userMail = userMail;
        this.userPw = userPw;
        this.userTel = userTel;
        this.userJob = userJob;
        this.userPlace = userPlace;
    }
}
