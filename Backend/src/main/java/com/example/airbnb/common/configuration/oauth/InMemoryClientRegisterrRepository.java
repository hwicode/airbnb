package com.example.airbnb.common.configuration.oauth;

import java.util.Map;

public class InMemoryClientRegisterrRepository {
    private Map<String, ClientRegistration> clientRegistration;

    public InMemoryClientRegisterrRepository(Map<String, ClientRegistration> clientRegistration) {
        this.clientRegistration = clientRegistration;
    }

    public ClientRegistration findByRegistration(String name) {
        return clientRegistration.get(name);
    }

    public InMemoryClientRegisterrRepository() {
    }
}
