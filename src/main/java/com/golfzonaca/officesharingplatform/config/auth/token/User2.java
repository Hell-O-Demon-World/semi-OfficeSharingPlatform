package com.golfzonaca.officesharingplatform.config.auth.token;

import lombok.Data;


@Data
public class User2 {
    private Long id;
    private String username;
    private String password;
    private String authority;
}
