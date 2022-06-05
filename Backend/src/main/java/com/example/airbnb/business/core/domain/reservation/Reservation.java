package com.example.airbnb.business.core.domain.reservation;

import com.example.airbnb.business.core.domain.accommodation.Accommodation;
import com.example.airbnb.business.core.domain.member.Member;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Entity
public class Reservation {

    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    private BigDecimal totalPrice;

    private Integer totalDay;

    private Integer totalPeople;

    private Integer adults;

    private Integer children;

    private Integer infants;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Embedded
    private Time time;

    @Builder
    public Reservation(BigDecimal totalPrice, Integer totalDay, Integer totalPeople, Integer adults, Integer children, Integer infants, Time time) {
        this.totalPrice = totalPrice;
        this.totalDay = totalDay;
        this.totalPeople = totalPeople;
        this.adults = adults;
        this.children = children;
        this.infants = infants;
        this.time = time;
    }

    protected Reservation (){};

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

    public void addInformation(Member member, Accommodation accommodation) {
        this.member = member;
        this.accommodation = accommodation;
    }
}
