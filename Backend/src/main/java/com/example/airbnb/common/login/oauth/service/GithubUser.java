package com.example.airbnb.common.login.oauth.service;

import com.example.airbnb.business.core.domain.member.Member;
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
        return GithubUser.builder()
                .githubId((String) attributes.get(GITHUB_ID))
                .profileImage((String) attributes.get(PROFILE_IMAGE))
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .githubId(githubId)
                .profileImage(profileImage)
                .build();
    }
}
