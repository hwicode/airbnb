package com.example.airbnb.common.login.oauth.controller.dto;

import lombok.Getter;

@Getter
public class Token {
    private String accessToken;
    private String refreshToken;

    public Token(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
