package com.example.airbnb.business.core.domain.reservation;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Embeddable
public class Time {

    private LocalDateTime checkinTime;
    private LocalDateTime checkoutTime;

    public Time(LocalDateTime checkinTime, LocalDateTime checkoutTime) {
        this.checkinTime = checkinTime;
        this.checkoutTime = checkoutTime;
    }

    protected Time() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return Objects.equals(checkinTime, time.checkinTime) && Objects.equals(checkoutTime, time.checkoutTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(checkinTime, checkoutTime);
    }
}
