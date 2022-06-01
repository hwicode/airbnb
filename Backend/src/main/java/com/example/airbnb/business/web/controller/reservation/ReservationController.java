package com.example.airbnb.business.web.controller.reservation;

import com.example.airbnb.business.web.controller.reservation.dto.DetailedReservationResponse;
import com.example.airbnb.business.web.controller.reservation.dto.ReservationListResponse;
import com.example.airbnb.business.web.controller.reservation.dto.ReservationResponse;
import com.example.airbnb.business.web.service.reservation.ReservationReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationReadService reservationReadService;

    @GetMapping("/{id}")
    public DetailedReservationResponse searchDetailedReservation(@PathVariable("id") Long id) {
        return reservationReadService.findByReservationId(id);
    }

    @GetMapping("/users/{githubId}")
    public List<ReservationResponse> searchReservations(@PathVariable String githubId) {
       return reservationReadService.findReservations(githubId);
    }

}
