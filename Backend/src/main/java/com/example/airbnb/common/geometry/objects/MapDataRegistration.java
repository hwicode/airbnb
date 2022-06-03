package com.example.airbnb.common.geometry.objects;

import com.example.airbnb.common.configuration.place.MapDataProperties;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class MapDataRegistration {

    private Map<String, String> registration = new HashMap<>();

    public MapDataRegistration(MapDataProperties mapDataProperties) {
        registration.put(mapDataProperties.getKey(), mapDataProperties.getApiKey());
        registration.put(mapDataProperties.getBaseUrlKey(), mapDataProperties.getBaseUrl());
        registration.put(mapDataProperties.getType(), mapDataProperties.getTypeValue());
    }

    public String getAttribute(String key) {
        return registration.get(key);
    }
}
