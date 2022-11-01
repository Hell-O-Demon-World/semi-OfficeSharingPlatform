package com.golfzonaca.backoffice.auth;

import lombok.Data;

@Data
public class TokenForm {
    private String accessToken;
    private String userId;
}
