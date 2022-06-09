package com.example.airbnb.business.core.domain.reservation;

import com.example.airbnb.business.core.domain.accommodation.Accommodation;
import com.example.airbnb.business.core.domain.member.Member;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Entity
public class Reservation {

    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @NotNull
    private BigDecimal totalPrice;

    @NotNull
    private Integer totalDay;

    @NotNull
    private Integer totalPeople;

    @NotNull
    private Integer adults;

    @NotNull
    private Integer children;

    @NotNull
    private Integer infants;

    @NotNull
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

    protected Reservation() {
    }


    public void addInformation(Member member, Accommodation accommodation) {
        validate(member, accommodation);
        this.member = member;
        this.accommodation = accommodation;
    }

    private void validate(Member member, Accommodation accommodation) {
        if (member == null || accommodation == null) {
            throw new IllegalArgumentException("회원, 숙소 정보를 입력해주세요.");
        }
    }

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
