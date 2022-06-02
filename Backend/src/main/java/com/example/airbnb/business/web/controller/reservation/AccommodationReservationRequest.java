package com.example.airbnb.business.web.controller.reservation;

import com.example.airbnb.business.core.domain.reservation.Reservation;
import com.example.airbnb.business.core.domain.reservation.Time;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class AccommodationReservationRequest {

    private Long accommodationId;
    private Integer adults;
    private Integer children;
    private Integer infants;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Integer totalPeople;
    private Integer totalDay;
    private BigDecimal pricePerDay;

    // 리팩토링
    public Reservation toEntity() {
        Time time = new Time(checkIn.atTime(0, 0), checkOut.atTime(9, 0));
        BigDecimal totalPrice = BigDecimal.valueOf((long) pricePerDay.intValue() * totalDay);
        return Reservation.builder()
                .totalDay(totalDay)
                .totalPrice(totalPrice)
                .adults(adults)
                .children(children)
                .infants(infants)
                .time(time)
                .build();
    }
}
