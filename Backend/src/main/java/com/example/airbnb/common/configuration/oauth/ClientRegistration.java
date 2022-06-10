package com.example.airbnb.common.configuration.oauth;

import lombok.Getter;

@Getter
public class ClientRegistration {
    private final String redirectUri;
    private final String tokenUrl;
    private final String userInfoUrl;
    private final String clientId;
    private final String clientSecret;
    private final String redirectUrl;
    private final String version;

    public static ClientRegistration bind(OauthClientProperties.Registration registration, OauthClientProperties.Provider provider) {
        return new ClientRegistration(registration.getRedirectUri(), provider.getTokenUri(), provider.getUserInfoUri(),
                registration.getClientId(), registration.getClientSecret(), registration.getRedirectUri(), provider.getVersion());
    }

    public ClientRegistration(String redirectUri, String tokenUrl, String userInfoUrl, String clientId, String clientSecret, String redirectUrl, String version) {
        this.redirectUri = redirectUri;
        this.tokenUrl = tokenUrl;
        this.userInfoUrl = userInfoUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUrl = redirectUrl;
        this.version = version;
    }
}
