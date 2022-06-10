package com.example.airbnb.business.web.controller.reservation.dto;

import com.example.airbnb.business.core.domain.reservation.Reservation;
import com.example.airbnb.business.core.domain.reservation.Time;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class AccommodationReservationRequest {

    @NotNull
    private Integer adults;

    @NotNull
    private Integer children;

    @NotNull
    private Integer infants;

    @NotNull
    private LocalDate checkIn;

    @NotNull
    private LocalDate checkOut;

    @NotNull
    private Integer totalPeople;

    @NotNull
    private Integer totalDay;

    @NotNull
    private BigDecimal pricePerDay;

    // TODO 리팩토링
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
