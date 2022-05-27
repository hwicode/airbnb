package com.example.airbnb.business.domain.accommodation;

import com.example.airbnb.business.domain.member.Member;

import javax.persistence.*;
import java.math.BigDecimal;

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

}
