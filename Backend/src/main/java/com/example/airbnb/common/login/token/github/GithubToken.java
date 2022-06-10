package com.example.airbnb.common.login.token.github;

import com.example.airbnb.common.login.token.WebToken;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GithubToken implements WebToken {

    public static final String GITHUB = "github";

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType;

}

