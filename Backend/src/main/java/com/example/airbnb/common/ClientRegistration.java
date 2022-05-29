package com.example.airbnb.common;

import com.example.airbnb.common.configuration.OauthClientProperties;
import lombok.Getter;

@Getter
public class ClientRegistration {

    private final String scope;
    private final String tokenUrl;
    private final String userInfoUrl;
    private final String clientId;
    private final String clientSecret;
    private final String redirectUrl;

    public ClientRegistration(String scope, String tokenUrl, String userInfoUrl, String clientId, String clientSecret, String redirectUrl) {
        this.scope = scope;
        this.tokenUrl = tokenUrl;
        this.userInfoUrl = userInfoUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUrl = redirectUrl;
    }

    public static ClientRegistration bind(OauthClientProperties.Registration registration, OauthClientProperties.Provider provider) {
        return new ClientRegistration("", provider.getTokenUri(), provider.getUserInfoUri(), registration.getClientId(), registration.getClientSecret(), registration.getRedirectUri());
    }
}
