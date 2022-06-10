package com.example.airbnb.common.configuration.oauth;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PermissionRequiredUrls {

    private static final String ACCOMMODATION_REGISTRATION = "/api/accommodations/registration";
    private static final String MEMBER = "/api/members/**";

    public List<String> getUrls() {
        Class<?> clazz = PermissionRequiredUrls.class;
        Field[] fields = clazz.getDeclaredFields();

        List<String> urls = new ArrayList<>();

        for (Field eachField : fields) {
            try {
                eachField.setAccessible(true);
                urls.add((String) eachField.get(clazz));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return urls;
    }
}
