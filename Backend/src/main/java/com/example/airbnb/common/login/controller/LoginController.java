package com.example.airbnb.common.login.controller;

import com.example.airbnb.common.login.controller.dto.LoginResponse;
import com.example.airbnb.common.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("login")
public class LoginController {

    private LoginService loginService;

    @GetMapping("github/{code}")
    public LoginResponse login(@PathVariable("code") String code) {
        return loginService.login(code);
    }
}
