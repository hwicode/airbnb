package com.example.airbnb.common.login.oauth.controller;

import com.example.airbnb.common.login.oauth.controller.dto.LoginResponse;
import com.example.airbnb.common.login.oauth.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("login")
public class LoginController {

    private final LoginService loginService;

    @GetMapping("github")
    public LoginResponse login(@RequestParam String code) {
        return loginService.login(code);
    }
}
