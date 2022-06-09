package com.example.airbnb.business.core.domain.accommodation;

import com.example.airbnb.business.web.controller.accommodation.dto.SearchPriceResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.airbnb.business.core.domain.accommodation.QAccommodation.accommodation;
import static com.example.airbnb.business.core.domain.accommodation.QAccommodationTag.accommodationTag;
import static com.example.airbnb.business.core.domain.reservation.QReservation.reservation;
import static com.example.airbnb.common.utils.TimeUtils.convertCheckin;
import static com.example.airbnb.common.utils.TimeUtils.convertCheckout;

@Repository
@RequiredArgsConstructor
public class TagReadRepository {

    private final JPAQueryFactory queryFactory;

    public Set<Long> getAccommodationsByTag(String tag) {
        QTag qTag = new QTag("tag");

        Set<Long> accommodationIds = new HashSet<>(queryFactory.select(accommodationTag.accommodation.accommodationId)
                .from(accommodationTag)
                .join(accommodationTag.tag, qTag)
                .on(qTag.name.eq(tag))
                .fetch());

        if (accommodationIds.isEmpty()) {
            return Collections.emptySet();
        }
        return accommodationIds;
    }

    public List<SearchPriceResponse> findAccommodationPriceRangeByTagAndPeriod(Set<Long> accommodations, LocalDate checkIn, LocalDate checkOut) {
        if (accommodations.isEmpty()) {
            return Collections.emptyList();
        }

//        List<Long> availableAccommodationsId = queryFactory.select(reservation.accommodation.accommodationId)
//                .from(reservation)
//                .join(reservation.accommodation, accommodation)
//                .on(accommodation.accommodationId.in(accommodations))
//                .where(reservation.time.checkinTime.after(convertCheckout(checkOut))
//                        .or(reservation.time.checkoutTime.before(convertCheckin(checkIn))))
//                .fetch();

        Set<Long> availableAccommodationsId2 = new HashSet<>(queryFactory.select(reservation.accommodation.accommodationId)
                .from(reservation)
                .join(reservation.accommodation, accommodation)
                .on(accommodation.accommodationId.in(accommodations))
                .where(reservation.time.checkinTime.between(convertCheckin(checkIn), convertCheckout(checkOut)),
                        reservation.time.checkoutTime.between(convertCheckin(checkIn), convertCheckout(checkOut)))
                .fetch());

        accommodations.removeAll(availableAccommodationsId2);

        if (accommodations.isEmpty()) {
            return Collections.emptyList();
        }

        return queryFactory.select(Projections.fields(SearchPriceResponse.class,
                        accommodation.accommodationId, accommodation.price))
                .from(accommodation)
                .where(accommodation.accommodationId.in(accommodations))
                .orderBy(accommodation.price.asc())
                .fetch();
    }
}
