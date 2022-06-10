package com.example.airbnb.business.core.repository.reservation.read;

import com.example.airbnb.business.web.controller.reservation.dto.DetailedReservationResponse;
import com.example.airbnb.business.web.controller.reservation.dto.ReservationResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.airbnb.business.core.domain.accommodation.QAccommodation.accommodation;
import static com.example.airbnb.business.core.domain.member.QMember.member;
import static com.example.airbnb.business.core.domain.reservation.QReservation.reservation;

@Repository
@RequiredArgsConstructor
public class ReservationReadRepository {

    private final JPAQueryFactory queryFactory;

    public Optional<DetailedReservationResponse> findReservationById(Long id) {
        DetailedReservationResponse detailedReservationResponse = queryFactory.select(
                        Projections.fields(DetailedReservationResponse.class,
                                reservation.reservationId, reservation.accommodation.accommodationId,
                                reservation.accommodation.name.as("roomName"), reservation.accommodation.address.homeAddress.as("address"),
                                reservation.time.checkinTime.as("checkIn"), reservation.time.checkoutTime.as("checkOut"),
                                member.memberId.as("hostId"), member.name.as("hostName"),
                                reservation.totalPeople, reservation.totalDay, reservation.totalPrice))
                .from(reservation)
                .leftJoin(reservation.member, member)
                .where(reservation.reservationId.eq(id))
                .fetchOne();

        return Optional.ofNullable(detailedReservationResponse);
    }

    public List<ReservationResponse> findReservationsByMemberId(Long id) {
        List<Long> result = queryFactory.select(reservation.reservationId)
                .where(reservation.member.memberId.eq(id)).fetch();

        return queryFactory.select(Projections.fields(ReservationResponse.class,
                        reservation.reservationId, reservation.accommodation.accommodationId.as("accommodationId"),
                        accommodation.name.as("roomName"), accommodation.address.homeAddress.as("address"),
                        accommodation.mainImageUrl.as("image"), reservation.time.checkinTime.as("checkIn"),
                        reservation.time.checkoutTime.as("checkOut")))
                .from(reservation)
                .join(reservation.accommodation, accommodation)
                .on(reservation.member.memberId.in(result))
                .fetch();
    }
}
