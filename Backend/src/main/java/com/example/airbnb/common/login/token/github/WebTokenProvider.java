package com.example.airbnb.common.login.token.github;

import com.example.airbnb.common.login.oauth.common.ClientRegistration;
import org.springframework.util.MultiValueMap;

import java.util.Map;

public interface WebTokenProvider extends TokenProvider {

    WebToken createToken(String code, ClientRegistration registration);

    MultiValueMap<String, Object> createRequestBody(String code, ClientRegistration registration);

    Map<String, Object> getAttributes(String accessToken, ClientRegistration clientRegistration);
}
