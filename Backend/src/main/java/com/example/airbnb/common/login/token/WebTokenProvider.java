package com.example.airbnb.common.login.token;

import com.example.airbnb.common.configuration.oauth.ClientRegistration;
import com.example.airbnb.common.login.token.TokenProvider;
import com.example.airbnb.common.login.token.WebToken;
import com.example.airbnb.common.login.token.github.GithubTokenProvider;
import org.springframework.util.MultiValueMap;

import java.util.Map;

public interface WebTokenProvider extends TokenProvider {

    WebToken createToken(String code, ClientRegistration registration);

    MultiValueMap<String, Object> createRequestBody(String code, ClientRegistration registration);

    Map<String, Object> getAttributes(String accessToken, ClientRegistration clientRegistration);

}
