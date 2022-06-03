package com.example.airbnb.common.login.oauth.controller.github;

import com.example.airbnb.common.configuration.oauth.ClientRegistration;
import com.example.airbnb.common.configuration.oauth.InMemoryClientRegisterrRepository;
import com.example.airbnb.common.login.oauth.controller.OauthController;
import com.example.airbnb.common.login.oauth.controller.dto.LoginResponse;
import com.example.airbnb.common.login.oauth.controller.dto.Token;
import com.example.airbnb.common.login.oauth.service.GithubUser;
import com.example.airbnb.common.login.oauth.service.LoginService;
import com.example.airbnb.common.login.token.WebToken;
import com.example.airbnb.common.login.token.WebTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.airbnb.common.login.token.github.GithubToken.GITHUB;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.HttpHeaders.SET_COOKIE;
import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth")
public class GithubOauthController implements OauthController {

    private final LoginService loginService;
    private final InMemoryClientRegisterrRepository inMemoryClientRegisterRepository;
    private final WebTokenProvider webTokenProvider;

    // 안드로이드 측요청: String
    @Override
    @GetMapping("/callback")
    public ResponseEntity<String> login(@RequestParam String code) {
        ClientRegistration clientRegistration = inMemoryClientRegisterRepository.findByRegistration(GITHUB);
        WebToken webToken = webTokenProvider.createToken(code, clientRegistration);
        System.out.println(webToken.getAccessToken());
        GithubUser githubUser = GithubUser.from(webTokenProvider.getAttributes(webToken.getAccessToken(), clientRegistration));
        loginService.save(githubUser);
        Token token = loginService.createToken(githubUser);
        return ResponseEntity.status(OK)
                .header(SET_COOKIE, createCookie(token.getRefreshToken()).toString())
                .body(token.getAccessToken());
    }
}
