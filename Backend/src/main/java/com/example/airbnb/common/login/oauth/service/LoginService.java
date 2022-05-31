package com.example.airbnb.common.login.oauth.service;

import com.example.airbnb.common.login.oauth.controller.dto.LoginResponse;

public interface LoginService {

    LoginResponse login(String code);
}
