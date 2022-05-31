package com.example.airbnb.common.login.token.github;

import com.example.airbnb.common.login.oauth.common.ClientRegistration;
import org.springframework.util.MultiValueMap;

public interface WebTokenProvider {

    WebToken createToken(String code, ClientRegistration registration);

    MultiValueMap<String, Object> createRequestBody(String code, ClientRegistration registration);
}
