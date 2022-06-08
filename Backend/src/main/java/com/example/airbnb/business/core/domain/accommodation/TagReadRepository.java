package com.example.airbnb.business.web.service.accommodation;

import com.example.airbnb.business.core.domain.accommodation.QTag;
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
import static com.example.airbnb.common.utils.TimeUtils.convert;

@Repository
@RequiredArgsConstructor
public class TagReadRepository {

    private final JPAQueryFactory queryFactory;

    // TODO. MysQL로 하는 검색 자체에 대한 의문 + 인덱스 타는 방식에 대한 고민 좀 고민
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

        // 예약 가능한 숙소 -> 인덱스 O
        List<Long> availableAccommodationsId = queryFactory.select(reservation.accommodation.accommodationId)
                .from(reservation)
                .join(reservation.accommodation, accommodation)
                .on(accommodation.accommodationId.in(accommodationIds))
                .where(reservation.time.checkinTime.after(convert(checkOut))
                        .or(reservation.time.checkoutTime.before(checkIn.atTime(9, 0, 1))))
                .fetch();

        if (availableAccommodationsId.isEmpty()) {
            return Collections.emptyList();
        }

        // 결과 -> PK 인덱스 O
        return queryFactory.select(Projections.fields(SearchPriceResponse.class,
                        accommodation.accommodationId, accommodation.price))
                .from(accommodation)
                .where(accommodation.accommodationId.in(availableAccommodationsId))
                .fetch();
    }


}
