package com.example.airbnb.common.login.oauth.controller;

import com.example.airbnb.common.login.oauth.common.ClientRegistration;
import com.example.airbnb.common.login.oauth.common.InMemoryClientRegisterrRepository;
import com.example.airbnb.common.login.oauth.controller.dto.OauthLoginResponse;
import com.example.airbnb.common.login.oauth.service.GithubUser;
import com.example.airbnb.common.login.oauth.service.LoginService;
import com.example.airbnb.common.login.token.JwtTokenProvider;
import com.example.airbnb.common.login.token.github.WebToken;
import com.example.airbnb.common.login.token.github.WebTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.airbnb.common.login.token.github.GithubToken.GITHUB;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth")
public class LoginController {

    private final LoginService loginService;
    private final InMemoryClientRegisterrRepository inMemoryClientRegisterRepository;
    private final WebTokenProvider webTokenProvider;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/login/github")
    public OauthLoginResponse login(@RequestParam String code) {
        ClientRegistration clientRegistration = inMemoryClientRegisterRepository.findByRegistration(GITHUB);
        WebToken webToken = webTokenProvider.createToken(code, clientRegistration);
        GithubUser githubUser = GithubUser.from(webTokenProvider.getAttributes(webToken.getAccessToken(), clientRegistration));

        String jwtToken = jwtTokenProvider.createJwtToken(githubUser.getGithubId());
        return loginService.login(code);
    }
}
