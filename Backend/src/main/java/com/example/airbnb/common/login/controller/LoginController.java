package com.example.airbnb.common.login.controller;

import com.example.airbnb.common.login.controller.dto.LoginResponse;
import com.example.airbnb.common.login.service.LoginService;
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
