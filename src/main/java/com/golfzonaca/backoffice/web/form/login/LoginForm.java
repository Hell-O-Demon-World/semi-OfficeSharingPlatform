package com.golfzonaca.backoffice.web.form.login;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginForm {

    @NotEmpty
    private String companyLoginId;
    @NotEmpty
    private String companyPw;
}