package com.example.airbnb.business.web.controller.reservation;

import com.example.airbnb.business.web.controller.reservation.dto.ReservationResponse;
import com.example.airbnb.business.web.service.reservation.ReservationReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationReadService reservationReadService;

    @GetMapping("id")
    public ReservationResponse searchReservationDetail(@PathVariable("id") Long id) {
        return reservationReadService.findByReservationId(id);
    }

}
