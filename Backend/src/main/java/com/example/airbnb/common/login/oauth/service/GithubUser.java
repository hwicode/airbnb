package com.example.airbnb.common.login.oauth.service;

import com.example.airbnb.business.core.domain.member.Member;

import java.util.Map;

public class GithubUser implements OauthClient {

    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PROFILE_IMAGE = "avatar_url";

    public static Member from(Map<String, Object> attributes) {
        return Member.builder()
                .name((String) attributes.get(NAME))
                .githubId((String) attributes.get(EMAIL))
                .githubId((String) attributes.get(PROFILE_IMAGE))
                .build();
    }
}
