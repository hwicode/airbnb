package com.example.airbnb.common.login.token.github;

import com.example.airbnb.common.configuration.oauth.ClientRegistration;
import com.example.airbnb.common.login.token.WebTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.Map;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
public class GithubTokenProvider implements WebTokenProvider {

    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String CODE = "code";

    @Override
    public GithubToken createToken(String code, ClientRegistration registration) {
        return WebClient.create()
                .post()
                .uri(URI.create(registration.getTokenUrl()))
                .contentType(APPLICATION_FORM_URLENCODED)
                .accept(APPLICATION_JSON)
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

    @Override
    public Map<String, Object> getAttributes(String accessToken, ClientRegistration clientRegistration) {
        return WebClient.create()
                .get()
                .uri(clientRegistration.getUserInfoUrl())
                .header(ACCEPT, clientRegistration.getVersion())
                .headers(header -> header.setBearerAuth(accessToken))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                })
                .block();
    }
}
