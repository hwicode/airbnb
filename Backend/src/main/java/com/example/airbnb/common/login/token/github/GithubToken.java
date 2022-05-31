package com.example.airbnb.common.login.token.github;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GithubToken implements WebToken {

    public static final String GITHUB = "github";
    private static final String TOKEN_DELIMETER = " ";

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType;

    @Override
    public String getAccessToken() {
        return tokenType + TOKEN_DELIMETER + accessToken;
    }
}

