package com.example.airbnb.business.core.domain.accommodation;

import com.example.airbnb.business.web.controller.accommodation.dto.SearchPriceResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static com.example.airbnb.business.core.domain.accommodation.QAccommodation.accommodation;
import static com.example.airbnb.business.core.domain.accommodation.QAccommodationTag.accommodationTag;
import static com.example.airbnb.business.core.domain.reservation.QReservation.reservation;
import static com.example.airbnb.common.utils.TimeUtils.convertCheckin;
import static com.example.airbnb.common.utils.TimeUtils.convertCheckout;

@Repository
@RequiredArgsConstructor
public class TagReadRepository {

    private final JPAQueryFactory queryFactory;

    public List<SearchPriceResponse> findAccommodationPriceRangeByTagAndPeriod(String tag, LocalDate checkIn, LocalDate checkOut) {
        QTag qTag = new QTag("tag");

        List<Long> accommodationIds = queryFactory.select(accommodationTag.accommodation.accommodationId)
                .from(accommodationTag)
                .join(accommodationTag.tag, qTag)
                .on(qTag.name.eq(tag))
                .fetch();

        if (accommodationIds.isEmpty()) {
            return Collections.emptyList();
        }

        List<Long> availableAccommodationsId = queryFactory.select(reservation.accommodation.accommodationId)
                .from(reservation)
                .join(reservation.accommodation, accommodation)
                .on(accommodation.accommodationId.in(accommodationIds))
                .where(reservation.time.checkinTime.after(convertCheckout(checkOut))
                        .or(reservation.time.checkoutTime.before(convertCheckin(checkIn))))
                .fetch();

        if (availableAccommodationsId.isEmpty()) {
            return Collections.emptyList();
        }

        return queryFactory.select(Projections.fields(SearchPriceResponse.class,
                        accommodation.accommodationId, accommodation.price))
                .from(accommodation)
                .where(accommodation.accommodationId.in(availableAccommodationsId))
                .fetch();
    }


}
