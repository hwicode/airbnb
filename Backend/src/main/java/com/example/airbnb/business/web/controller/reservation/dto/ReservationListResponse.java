package com.example.airbnb.business.web.controller.reservation.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ReservationListResponse {

    private List<ReservationResponse> reservations;

    public ReservationListResponse(List<ReservationResponse> reservations) {
        this.reservations = reservations;
    }
}
