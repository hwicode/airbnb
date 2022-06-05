package com.example.airbnb.common.login.oauth.service;


import com.example.airbnb.common.login.oauth.controller.dto.Token;

public interface LoginService {

    void save(GithubUser code);

    Token createToken(GithubUser githubUser);

    void saveToken(String key, String value);
}
