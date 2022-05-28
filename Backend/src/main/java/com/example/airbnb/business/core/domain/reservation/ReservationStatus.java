package com.example.airbnb.business.core.domain.reservation;

import lombok.Getter;

@Getter
public enum ReservationStatus {
    CANCEL,
    BOOKED,
    BOOKABLE;
}
