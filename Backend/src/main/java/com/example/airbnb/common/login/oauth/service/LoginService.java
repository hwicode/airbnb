package com.example.airbnb.common.login.oauth.service;

import com.example.airbnb.common.login.oauth.controller.dto.OauthLoginResponse;

public interface LoginService {

    OauthLoginResponse login(String code);
}
