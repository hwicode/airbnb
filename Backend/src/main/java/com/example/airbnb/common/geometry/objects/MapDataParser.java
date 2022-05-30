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

    public Map<String, String> parse(JSONObject geometryData) {
        Map<String, String> geometryDataMap = new HashMap<>();

        double latitude = getAttribute(geometryData, LATITUDE);
        double longitude = getAttribute(geometryData, LONGITUDE);

        geometryDataMap.put(LATITUDE, Double.toString(latitude));
        geometryDataMap.put(LONGITUDE, Double.toString(longitude));
        return geometryDataMap;
    }

    private double getAttribute(JSONObject jsonObject, String latitude) throws JSONException {
        return jsonObject.getJSONObject(GEOMETRY).getJSONObject(LOCATION).getDouble(latitude);
    }
}

