package com.example.airbnb.business.web.controller.reservation;

import com.example.airbnb.business.web.controller.accommodation.AccommodationRegistRequest;
import com.example.airbnb.business.web.controller.accommodation.AccommodationRegistResponse;
import com.example.airbnb.business.web.controller.reservation.dto.DetailedReservationResponse;
import com.example.airbnb.business.web.controller.reservation.dto.ReservationListResponse;
import com.example.airbnb.business.web.controller.reservation.dto.ReservationResponse;
import com.example.airbnb.business.web.service.reservation.ReservationReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationReadService reservationReadService;

    @GetMapping("/{id}")
    public DetailedReservationResponse searchDetailedReservation(@PathVariable("id") Long id) {
        return reservationReadService.findByReservationId(id);
    }

    @GetMapping("/users/{githubId}")
    public List<ReservationResponse> searchReservations(@PathVariable String githubId) {
       return reservationReadService.findReservations(githubId);
    }

    @PostMapping
    public List<AccommodationReservationResponse> createReservation(@RequestBody AccommodationReservationRequest request) {
        return null;
    }

}
