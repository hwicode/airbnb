package com.example.airbnb.common.login.oauth.service;

import com.example.airbnb.business.core.domain.member.Member;
import com.example.airbnb.business.core.repository.member.MemberRepository;
import com.example.airbnb.common.login.oauth.controller.dto.Token;
import com.example.airbnb.common.login.token.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.airbnb.business.core.domain.member.Member.createMember;
import static com.example.airbnb.common.login.token.jwt.JwtTokenProvider.FIFTEEN_MINUTE;
import static com.example.airbnb.common.login.token.jwt.JwtTokenProvider.ONE_WEEK;

@Service
@RequiredArgsConstructor
public class GithubLoginService implements LoginService {

    private final Logger logger = LoggerFactory.getLogger(GithubLoginService.class);
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    @Transactional
    public void save(GithubUser githubUser) {
        Optional<Member> findMember = memberRepository.findByGithubId(githubUser.getGithubId());
        if (findMember.isPresent()) {
            Member member = findMember.get();
            member.updateProfile(githubUser.getProfileImage());
            logger.info("회원 업데이트:{}", member.getMemberId());
            return;
        }
        memberRepository.save(createMember(githubUser.getGithubId(), githubUser.getProfileImage()));
        logger.info("회원 저장:{}", githubUser.getGithubId());
    }

    @Override
    public Token createToken(GithubUser githubUser) {
        String accessToken = jwtTokenProvider.createToken(githubUser.getGithubId(), FIFTEEN_MINUTE);
        String refreshToken = jwtTokenProvider.createToken(githubUser.getGithubId(), ONE_WEEK);
        saveToken(githubUser.getGithubId(), refreshToken);
        logger.info("토큰 발급 accessToken:{}, refreshToken:{}", accessToken, refreshToken);
        return new Token(accessToken, refreshToken);
    }

    @Override
    public void saveToken(String key, String value) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
//        values.set(key, value, Duration.ofDays(7));
        logger.info("토큰 저장:{}", value);
    }
}

