package com.example.airbnb.business.web.controller.reservation.dto;

import com.example.airbnb.business.core.domain.reservation.Reservation;
import com.example.airbnb.business.web.controller.accommodation.dto.AccommodationOptionLineResponse;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class AccommodationReservationResponse {

    private Long reservationId;
    private Long accommodationId;

    private String roomName;
    private String address;
    private String imageUrl;
    private String pricePerDay;
    private String totalPrice;
    private List<AccommodationOptionLineResponse> accommodationOptionLines;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private int totalDay;
    private int adult;
    private int children;
    private int infants;
    private int totalPeople;

    public AccommodationReservationResponse(Reservation reservation) {
        this.reservationId = reservation.getReservationId();
        this.accommodationId = accommodationId;
//        this.roomName = reservation.getn;
        this.address = address;
        this.imageUrl = imageUrl;
        this.pricePerDay = pricePerDay;
        this.totalPrice = totalPrice;
        this.accommodationOptionLines = accommodationOptionLines;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalDay = totalDay;
        this.adult = adult;
        this.children = children;
        this.infants = infants;
        this.totalPeople = totalPeople;
    }
}
