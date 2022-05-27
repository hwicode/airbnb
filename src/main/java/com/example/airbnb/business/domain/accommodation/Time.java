package com.example.airbnb.business.domain.accommodation;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Getter
@Embeddable
public class Time {

    private LocalDateTime checkinTime;
    private LocalDateTime checkoutTime;
}
