package com.example.airbnb.business.core.domain.reservation;

import com.example.airbnb.business.core.domain.accommodation.Accommodation;
import com.example.airbnb.business.core.domain.member.Member;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Reservation {

    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationId;

    private BigDecimal totalPrice;

    private int totalDay;

    private int totalPeople;

    private int adults;

    private int children;

    private int infants;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Embedded
    private Time time;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return reservationId.equals(that.reservationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId);
    }
}
