package com.example.airbnb.common.geometry.objects;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class MapDataConverter {

    private static final String RESULTS = "results";
    private static final int FIRST = 0;

    public JSONObject convert(String mapData) {
        JSONObject jsonObject = new JSONObject(mapData);
        JSONArray jsonArray = jsonObject.getJSONArray(RESULTS);
        return jsonArray.getJSONObject(FIRST);
    }
}
