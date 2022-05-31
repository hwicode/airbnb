package com.example.airbnb.common.login.token.github;

import com.example.airbnb.common.login.oauth.common.ClientRegistration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

@Component
public class GithubTokenProvider implements WebTokenProvider {

    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String CODE = "code";

    public GithubTokenProvider(){};

    @Override
    public GithubToken createToken(String code, ClientRegistration registration) {
        return WebClient.create()
                .post()
                .uri(URI.create(registration.getTokenUrl()))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(createRequestBody(code, registration))
                .retrieve()
                .bodyToMono(GithubToken.class)
                .block();
    }

    @Override
    public MultiValueMap<String, Object> createRequestBody(String code, ClientRegistration registration) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add(CLIENT_ID, registration.getClientId());
        body.add(CLIENT_SECRET, registration.getClientSecret());
        body.add(CODE, code);
        return body;
    }
}
