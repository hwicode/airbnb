package com.example.airbnb.business.core.repository.accommodation.querydsl;

import com.example.airbnb.business.core.domain.accommodation.Amenity;
import com.example.airbnb.business.core.domain.accommodation.AmenityCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.airbnb.business.core.domain.accommodation.QAccommodation.accommodation;
import static com.example.airbnb.business.core.domain.accommodation.QAmenity.amenity;
import static com.example.airbnb.business.core.domain.accommodation.QAmenityCategory.amenityCategory;

@Repository
@RequiredArgsConstructor
public class AmenityReadRepository {

    private final JPAQueryFactory queryFactory;

    public List<Amenity> findAmenityCategoriesByAccommodation(Long id) {
        return queryFactory.selectFrom(amenity)
                .join(amenity.accommodation, accommodation)
                .on(accommodation.accommodationId.eq(id))
                .join(amenity.amenityCategory, amenityCategory)
                .fetchJoin()
                .fetch();
    }
}
