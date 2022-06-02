package com.example.airbnb.business.core.domain.accommodation;

import lombok.Getter;

@Getter
public enum AmenityType {
    BATHROOM("욕실"),
    BEDROOM_AND_LAUNDRY("침실 및 세탁시설"),
    INTERNET("인터넷"),
    KITCHEN("주방"),
    SERVICE("서비스"),
    FAMILY("가족"),
    HEATING_COOLING("냉-난방");

    private final String name;

    AmenityType(String name) {
        this.name = name;
    }
}
