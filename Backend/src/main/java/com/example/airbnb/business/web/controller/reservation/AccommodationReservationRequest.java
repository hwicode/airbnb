package com.example.airbnb.business.web.controller.reservation;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class AccommodationReservationRequest {

    private Long accommodationId;
    private int adults;
    private int children;
    private int infants;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int totalPeople;
    private int totalDay;
    private BigDecimal totalPrice;
}
