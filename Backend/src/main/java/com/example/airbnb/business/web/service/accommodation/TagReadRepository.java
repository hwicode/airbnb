package com.example.airbnb.business.web.service.accommodation;

import com.example.airbnb.business.core.domain.accommodation.AccommodationTag;
import com.example.airbnb.business.core.domain.accommodation.QTag;
import com.example.airbnb.business.web.controller.accommodation.SearchPriceResponse;
import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.airbnb.business.core.domain.accommodation.QAccommodation.accommodation;
import static com.example.airbnb.business.core.domain.accommodation.QAccommodationTag.accommodationTag;

@Repository
@RequiredArgsConstructor
public class TagReadRepository {

    private final JPAQueryFactory queryFactory;

    public List<SearchPriceResponse> findAccommodationTagBy(String tag) {
        QTag qTag = new QTag("tag");
        return queryFactory.select(
                        Projections.fields(SearchPriceResponse.class,
                                accommodationTag.accommodationTagId, qTag.tagId,
                                accommodation.price))
                .from(accommodationTag)
                .join(accommodationTag.tag, qTag)
                .on(qTag.name.eq(tag))
                .join(accommodationTag.accommodation, accommodation)
                .fetch();
    }
}
