package com.golfzonaca.officesharingplatform.web.auth.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignInSaveForm {
    private String username;
    private String password;
}
