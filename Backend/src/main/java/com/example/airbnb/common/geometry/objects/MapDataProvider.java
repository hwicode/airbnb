package com.example.airbnb.common.geometry.objects;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class MapDataProvider {

    private static final String KEY = "key";
    private static final String URL = "baseUrl";
    private static final String ADDRESS = "address";
    private static final String TYPE = "type";

    private final MapDataParser parser;
    private final MapDataConverter converter;

    public Map<String, String> getGeometryData(String address, MapDataRegistration registration) {
        String data = getData(address, registration);
        return convert(data);
    }

    private String getData(String address, MapDataRegistration registration) {
        return WebClient.create(registration.getAttribute(URL))
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(registration.getAttribute(TYPE))
                        .queryParam(ADDRESS, address)
                        .queryParam(KEY, registration.getAttribute(KEY))
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    private Map<String, String> convert(String data) {
        JSONObject geometryData = converter.convert(data);
        return parse(geometryData);
    }

    private Map<String, String> parse(JSONObject geometryData) {
        return parser.parse(geometryData);
    }
}
