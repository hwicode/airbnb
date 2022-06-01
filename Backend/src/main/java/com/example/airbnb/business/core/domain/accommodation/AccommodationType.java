package com.example.airbnb.business.core.domain.accommodation;

import lombok.Getter;

@Getter
public enum AccommodationType {

    HOUSE("개인 주택"),
    HOTEL("호텔"),
    GUEST_HOUSE("게스트 하우스"),
    PENSION("펜션");

    private final String value;

    AccommodationType(String value) {
        this.value = value;
    }
}
