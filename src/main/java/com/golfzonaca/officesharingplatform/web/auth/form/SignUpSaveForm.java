package com.golfzonaca.officesharingplatform.web.auth.form;

import com.golfzonaca.officesharingplatform.domain.User;
import com.golfzonaca.officesharingplatform.repository.mileage.MemoryMileageRepository;
import com.golfzonaca.officesharingplatform.service.MileageService;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpSaveForm {
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private String job;
    private String preferType;

    public SignUpSaveForm(String email, String password, String name, String phoneNumber, String job, String preferType) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.job = job;
        this.preferType = preferType;
    }

    public User toEntity() {
        return new User(name, email, password, phoneNumber, job, preferType);
    }
}
