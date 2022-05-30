package com.example.airbnb.common.configuration.place;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Getter
@Component
public class MapDataProperties {

    @Value("${map.client.registration.key}")
    private String key;

    @Value("${map.client.registration.api-key}")
    private String apiKey;

    @Value("${map.client.registration.base-url-key}")
    private String baseUrlKey;

    @Value("${map.client.registration.base-url}")
    private String baseUrl;

    @Value("${map.client.registration.type}")
    private String type;

    @Value("${map.client.registration.type-value}")
    private String typeValue;

    @Value("${map.client.registration.url-delimeter}")
    private String urlDelimeter;

    public MapDataProperties(String key, String apiKey, String baseUrlKey, String baseUrl, String type, String urlDelimeter) {
        this.key = key;
        this.apiKey = apiKey;
        this.baseUrlKey = baseUrlKey;
        this.baseUrl = baseUrl;
        this.type = type;
        this.urlDelimeter = urlDelimeter;
    }

    public MapDataProperties() {
    }

//    @Bean
//    public MapDataRegistration mapRegistration() {
//        return new MapDataRegistration(this);
//    }
}
