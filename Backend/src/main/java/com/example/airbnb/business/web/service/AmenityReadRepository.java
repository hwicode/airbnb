package com.example.airbnb.business.web.service;

import com.example.airbnb.business.core.domain.accommodation.AmenitySubCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.airbnb.business.core.domain.accommodation.QAccommodation.accommodation;
import static com.example.airbnb.business.core.domain.accommodation.QAmenityCategory.amenityCategory;
import static com.example.airbnb.business.core.domain.accommodation.QAmenitySubCategory.amenitySubCategory;

@Repository
@RequiredArgsConstructor
public class AmenityReadRepository {

    private final JPAQueryFactory queryFactory;

    public List<AmenitySubCategory> findAmenityCategoryByAccommodation(Long id) {
        return queryFactory.selectFrom(amenitySubCategory)
                .join(amenitySubCategory.amenityCategory, amenityCategory)
                .fetchJoin()
                .join(amenityCategory.accommodation, accommodation)
                .fetchJoin()
                .where(accommodation.accommodationId.eq(id))
                .fetch();
    }
}
