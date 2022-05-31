package com.example.airbnb.common.login.service;

import com.example.airbnb.common.login.controller.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GithubLoginService implements LoginService {

    @Override
    @Transactional
    public LoginResponse login(String code) {
        return new LoginResponse();
    }

}
