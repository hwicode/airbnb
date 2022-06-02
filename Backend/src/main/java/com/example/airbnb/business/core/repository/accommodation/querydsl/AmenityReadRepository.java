package com.example.airbnb.business.core.repository.accommodation.querydsl;

import com.example.airbnb.business.core.domain.accommodation.AmenityCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.airbnb.business.core.domain.accommodation.QAccommodation.accommodation;
import static com.example.airbnb.business.core.domain.accommodation.QAmenityCategory.amenityCategory;

@Repository
@RequiredArgsConstructor
public class AmenityReadRepository {

    private final JPAQueryFactory queryFactory;

    public List<AmenityCategory> findAmenityCategoriesByAccommodation(Long id) {
        return null;
//        return queryFactory.selectFrom(amenitySubCategory)
//                .join(amenitySubCategory.amenityCategory, amenityCategory)
//                .fetchJoin()
//                .join(amenityCategory.accommodation, accommodation)
//                .fetchJoin()
//                .where(accommodation.accommodationId.eq(id))
//                .fetch();
    }
}
