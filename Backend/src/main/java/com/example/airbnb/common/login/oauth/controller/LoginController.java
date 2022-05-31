package com.example.airbnb.common.login.oauth.controller;

import com.example.airbnb.common.login.oauth.controller.dto.OauthLoginResponse;
import com.example.airbnb.common.login.oauth.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth")
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login/github")
    public OauthLoginResponse login(@RequestParam String code) {
        return loginService.login(code);
    }
}
