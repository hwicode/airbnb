package com.example.airbnb.business.web.controller.reservation;

import com.example.airbnb.business.core.domain.accommodation.Accommodation;
import com.example.airbnb.business.core.domain.accommodation.AccommodationType;
import com.example.airbnb.business.core.domain.reservation.Reservation;
import com.example.airbnb.business.core.repository.accommodation.AccommodationRepository;
import com.example.airbnb.business.core.repository.reservation.ReservationRepository;
import com.example.airbnb.business.web.service.accommodation.AccommodationReadService;
import com.example.airbnb.common.exception.BusinessException;
import com.example.airbnb.common.exception.accommodation.AccommodationTypeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {

    private AccommodationRepository accommodationRepository;
    private ReservationRepository reservationRepository;

    public List<AccommodationReservationResponse> reservation(Long accommodationId, AccommodationReservationRequest request) {
        Accommodation findAccommodation = accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new BusinessException(AccommodationTypeException.ACCOMMODATION_NOT_FOUND));

        Reservation reservation = new Reservation();
        return null;
    }
}
