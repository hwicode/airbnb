package com.example.airbnb.common.configuration.oauth;

import com.example.airbnb.common.login.token.ClientRegistration;
import com.example.airbnb.common.login.token.InMemoryClientRegisterrRepository;
import com.example.airbnb.common.login.token.OauthClientProperties;
import com.example.airbnb.common.login.token.OauthClientPropertiesRegistrationAdapter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@EnableConfigurationProperties(OauthClientProperties.class)
public class OauthConfiguration {

    private final OauthClientProperties oauthClientProperties;

    public OauthConfiguration(OauthClientProperties oauthClientProperties) {
        this.oauthClientProperties = oauthClientProperties;
    }

    @Bean
    public InMemoryClientRegisterrRepository inMemoryProviderRepository() {
        Map<String, ClientRegistration> providers = OauthClientPropertiesRegistrationAdapter.getOauthProviders(oauthClientProperties);
        return new InMemoryClientRegisterrRepository(providers);
    }

    public OauthClientProperties getOauthClientProperties() {
        return oauthClientProperties;
    }
}
