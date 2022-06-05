package com.example.airbnb.common.geometry.objects;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class MapDataProvider {

    private static final String KEY = "key";
    private static final String URL = "baseUrl";
    private static final String ADDRESS = "address";
    private static final String TYPE = "type";

    private final MapDataParser parser;
    private final MapDataConverter converter;

    public Position getGeometryData(String address, MapDataRegistration registration) {
        String data = getData(address, registration);
        if (contains(data)) {
            return convert(data);
        }
        throw new IllegalArgumentException("잘못된 위치 입니다.");
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

    private boolean contains(String data) {
        return data!= null && data.length() > 0;
    }

    private Position convert(String data) {
        JSONObject geometryData = converter.convert(data);
        return parse(geometryData);
    }

    private Position parse(JSONObject geometryData) {
        return parser.parse(geometryData);
    }
}
