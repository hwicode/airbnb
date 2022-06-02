package com.example.airbnb.business.web.controller.reservation;

import com.example.airbnb.business.web.controller.reservation.dto.DetailedReservationResponse;
import com.example.airbnb.business.web.controller.reservation.dto.ReservationResponse;
import com.example.airbnb.business.web.service.reservation.ReservationReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
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

    // 리팩토링
    @PostMapping("")
    public AccommodationReservationResponse reserveAccommodation(HttpServletRequest servletRequest,  @RequestParam("accommodationId") Long accommodationId, @RequestBody AccommodationReservationRequest request) {
        String githubId = (String) servletRequest.getHeader("token");
        return new AccommodationReservationResponse(reservationService.reservation(githubId, accommodationId, request));
    }

}
