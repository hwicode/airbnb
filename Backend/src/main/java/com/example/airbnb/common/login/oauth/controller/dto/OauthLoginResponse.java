package com.example.airbnb.common.login.oauth.controller.dto;

import lombok.Getter;

@Getter
public class OauthLoginResponse {

    private String accessToken;
    private String refreshToken;

    public OauthLoginResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public OauthLoginResponse() {

    }
}
