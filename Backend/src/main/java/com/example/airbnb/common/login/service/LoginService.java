package com.example.airbnb.common.login.service;

import com.example.airbnb.common.login.controller.dto.LoginResponse;

public interface LoginService {

    LoginResponse login(String code);
}
