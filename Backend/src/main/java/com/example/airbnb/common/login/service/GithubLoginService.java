package com.example.airbnb.common.login.service;

import com.example.airbnb.business.core.repository.member.MemberRepository;
import com.example.airbnb.common.login.controller.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GithubLoginService implements LoginService {

    private final MemberRepository memberRepository;
    @Override
    public LoginResponse login(String code) {
        return null;
    }
}
