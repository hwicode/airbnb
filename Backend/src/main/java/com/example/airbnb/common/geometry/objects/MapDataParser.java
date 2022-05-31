package com.example.airbnb.common.geometry.objects;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MapDataParser {

    private static final String LATITUDE = "lat";
    private static final String LONGITUDE = "lng";

    private static final String GEOMETRY = "geometry";
    private static final String LOCATION = "location";

    public Map<String, Object> parse(JSONObject geometryData) {
        Map<String, Object> geometryDataMap = new HashMap<>();

        Double latitude = getAttribute(geometryData, LATITUDE);
        Double longitude = getAttribute(geometryData, LONGITUDE);

        geometryDataMap.put(LATITUDE, Double.toString(latitude));
        geometryDataMap.put(LONGITUDE, Double.toString(longitude));
        return geometryDataMap;
    }

    private Double getAttribute(JSONObject jsonObject, String latitude) {
        try {
            return jsonObject.getJSONObject(GEOMETRY).getJSONObject(LOCATION).getDouble(latitude);
        } catch (JSONException e) {
            throw new IllegalArgumentException("잘못된 형식의 데이터입니다.");
        }
    }
}

