package com.example.airbnb.business.web.controller.reservation.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReservationResponse {

    private Long reservationId;
    private Long accommodationId;
    private String roomName;
    private String address;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private String image;

    public ReservationResponse() {
    }
}
