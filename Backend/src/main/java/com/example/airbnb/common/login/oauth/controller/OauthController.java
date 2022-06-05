package com.example.airbnb.common.login.oauth.controller;

import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;

public interface OauthController {

    ResponseEntity<String> login(String code);

    default ResponseCookie createCookie(String token) {
        return ResponseCookie.from("refreshToken", token)
                .httpOnly(true)
                .secure(true)
                .maxAge(1000 * 60 * 12 * 24 * 7)
                .path("/")
                .build();
    }
}
