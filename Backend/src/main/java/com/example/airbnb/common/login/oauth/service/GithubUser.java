package com.example.airbnb.common.login.oauth.service;

import com.example.airbnb.business.core.domain.member.Member;
import com.example.airbnb.common.login.token.OauthClient;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class GithubUser implements OauthClient {

    private static final String GITHUB_ID = "login";
    private static final String PROFILE_IMAGE = "avatar_url";

    private String githubId;
    private String profileImage;

    @Builder
    public GithubUser(String githubId, String profileImage) {
        this.githubId = githubId;
        this.profileImage = profileImage;
    }

    public static GithubUser from(Map<String, Object> attributes) {
        validate(attributes);
        return GithubUser.builder()
                .githubId((String) attributes.get(GITHUB_ID))
                .profileImage((String) attributes.get(PROFILE_IMAGE))
                .build();
    }

    private static void validate(Map<String, Object> attributes) {
        String githubId = (String) attributes.get(GITHUB_ID);
        if (githubId == null) {
            throw new IllegalArgumentException("잘못된 사용자입니다.");
        }
    }

    public Member toEntity() {
        return Member.builder()
                .githubId(githubId)
                .profileImage(profileImage)
                .build();
    }
}
