package com.example.airbnb.business.web.service;

import com.example.airbnb.business.core.domain.accommodation.Image;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.airbnb.business.core.domain.accommodation.QAccommodation.accommodation;
import static com.example.airbnb.business.core.domain.accommodation.QImage.image;

@Repository
@RequiredArgsConstructor
public class ImageReadRepository {

    private final JPAQueryFactory queryFactory;

    public List<Image> findImageByAccommodation(Long id) {
        return queryFactory.selectFrom(image)
                .join(image.accommodation, accommodation)
                .on(accommodation.accommodationId.eq(id))
                .fetch();
    }

}
