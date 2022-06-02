package com.example.airbnb.common.login.oauth.service;

import com.example.airbnb.business.core.domain.member.Member;
import com.example.airbnb.business.core.repository.member.MemberRepository;
import com.example.airbnb.common.login.oauth.controller.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class GithubLoginService implements LoginService {

    private final Logger logger = LoggerFactory.getLogger(GithubLoginService.class);
    private final MemberRepository memberRepository;
    private final WebTokenRepository webTokenRepository;
    private final RedisTemplate<String, String> redisTemplate;

    public void setValues(String name, String age) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(name, age, Duration.ofMinutes(5));
    }

    public String getValues(String name) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        return values.get(name);
    }

    @Override
    @Transactional
    public LoginResponse login(GithubUser code) {
        setValues(code.getGithubId(), code.getGithubId());
        return new LoginResponse();
    }

    @Override
    public void save(String jwtToken) {
        String findToken = "";
        if (findToken != null) {
            // 타당한 토큰이면 검증.
            // 유효 기간이 지났으면 삭제

            // 있으면
        }
    }

    // 구현 미완
    @Transactional
    public String save(GithubUser githubUser, String jwtToken) {
        Member findMember = memberRepository.findByGithubId(githubUser.getGithubId())
                .orElseGet(githubUser::toEntity);
        if (findMember.isNewUser()) {
            memberRepository.save(findMember);
        }
        return jwtToken;
    }

}
