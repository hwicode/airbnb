package com.example.airbnb.common.login.oauth.service;

import com.example.airbnb.business.core.domain.member.Member;
import com.example.airbnb.business.core.repository.member.MemberRepository;
import com.example.airbnb.common.login.oauth.common.ClientRegistration;
import com.example.airbnb.common.login.oauth.common.InMemoryClientRegisterrRepository;
import com.example.airbnb.common.login.oauth.controller.dto.OauthLoginResponse;
import com.example.airbnb.common.login.token.github.WebToken;
import com.example.airbnb.common.login.token.github.WebTokenProvider;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.airbnb.common.login.token.github.GithubToken.GITHUB;

@Service
@RequiredArgsConstructor
public class GithubLoginService implements LoginService {

    private final Logger logger = LoggerFactory.getLogger(GithubLoginService.class);
    private final MemberRepository memberRepository;
    private final InMemoryClientRegisterrRepository inMemoryClientRegisterRepository;
    private final WebTokenProvider webTokenProvider;

    @Override
    @Transactional
    public OauthLoginResponse login(String code) {
        ClientRegistration clientRegistration = inMemoryClientRegisterRepository.findByRegistration(GITHUB);
        WebToken webToken = webTokenProvider.createToken(code, clientRegistration);
        Member member = GithubUser.from(webTokenProvider.getAttributes(webToken.getAccessToken(), clientRegistration));
        return new OauthLoginResponse();
    }

}
