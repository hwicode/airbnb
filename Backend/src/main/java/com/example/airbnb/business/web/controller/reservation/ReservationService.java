package com.example.airbnb.business.web.controller.reservation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {

    public List<AccommodationReservationResponse> reservation(Long accommodationId, AccommodationReservationRequest request) {
        return null;
    }
}
