package com.example.airbnb.common.login.service;

import com.example.airbnb.common.login.controller.dto.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public class GithubLoginService implements LoginService {

    @Override
    public LoginResponse login(String code) {
        return null;
    }
}
