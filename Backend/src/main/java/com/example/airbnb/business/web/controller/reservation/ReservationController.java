package com.example.airbnb.business.web.controller.reservation;

import com.example.airbnb.business.web.controller.reservation.dto.AccommodationReservationRequest;
import com.example.airbnb.business.web.controller.reservation.dto.AccommodationReservationResponse;
import com.example.airbnb.business.web.controller.reservation.dto.DetailedReservationResponse;
import com.example.airbnb.business.web.controller.reservation.dto.ReservationResponse;
import com.example.airbnb.business.web.service.reservation.ReservationReadService;
import com.example.airbnb.common.login.token.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationReadService reservationReadService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/{id}")
    public DetailedReservationResponse searchDetailedReservation(@PathVariable("id") Long id) {
        return reservationReadService.findByReservationId(id);
    }

    @GetMapping
    public List<ReservationResponse> searchReservations(HttpServletRequest httpServletRequest) {
        String githubId = jwtTokenProvider.getAttribute(httpServletRequest.getHeader("Authorization"));
        log.info("숙소 조회: {}", githubId);
        return reservationReadService.findReservationsBy(githubId);
    }

    @PostMapping
    public AccommodationReservationResponse reserveAccommodation(HttpServletRequest httpServletRequest, @RequestParam("accommodationId") Long accommodationId,
                                                                 @RequestBody AccommodationReservationRequest request) {
        String githubId = jwtTokenProvider.getAttribute(httpServletRequest.getHeader("Authorization"));
        log.info("숙소 조회: {}", githubId);
        return new AccommodationReservationResponse(reservationService.reservation(githubId, accommodationId, request));
    }

}
