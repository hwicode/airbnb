package com.example.airbnb.configuration.data;

import com.example.airbnb.business.core.domain.reservation.Reservation;
import com.example.airbnb.business.core.domain.reservation.Time;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class ReservationData {

    private Reservation reservation;

    public ReservationData() {
        this.reservation = createReservation();
    }

    private Reservation createReservation() {
        return Reservation.builder()
                .totalPrice(new BigDecimal("39800"))
                .totalDay(3)
                .totalPeople(3)
                .adults(2)
                .children(1)
                .time(createTime(LocalDateTime.of(2022, 6, 10, 0, 0, 0),
                        LocalDateTime.of(2022, 6, 15, 0, 0, 0)))
                .build();
    }

    private Time createTime(LocalDateTime checkIn, LocalDateTime checkOut) {
        return new Time(checkIn, checkOut);
    }

}
