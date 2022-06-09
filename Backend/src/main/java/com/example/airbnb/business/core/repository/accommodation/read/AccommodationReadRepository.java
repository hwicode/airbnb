package com.example.airbnb.business.core.repository.accommodation.querydsl;

import com.example.airbnb.business.core.domain.accommodation.AccommodationOptionLine;
import com.example.airbnb.business.web.controller.accommodation.dto.*;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.example.airbnb.business.core.domain.accommodation.QAccommodation.accommodation;
import static com.example.airbnb.business.core.domain.accommodation.QAccommodationOptionLine.accommodationOptionLine;
import static com.example.airbnb.business.core.domain.accommodation.QCity.city;
import static com.example.airbnb.business.core.domain.member.QMember.member;
import static com.example.airbnb.common.utils.PageUtils.NEXT;
import static com.example.airbnb.common.utils.PageUtils.getHasNext;

@Repository
@RequiredArgsConstructor
public class AccommodationReadRepository {

    private final JPAQueryFactory queryFactory;

    public Optional<AccommodationResponse> findAccommodationById(Long id) {
        AccommodationResponse accommodationDetail = queryFactory.select(
                        Projections.fields(AccommodationResponse.class,
                                accommodation.accommodationId, member.memberId.as("hostId"),
                                member.name.as("hostName"), member.profileImage.as("hostProfileImage"), accommodation.name,
                                accommodation.description, accommodation.price.as("oneDayPerPrice"),
                                accommodation.address.homeAddress.as("address"), accommodation.accommodationType,
                                accommodation.averageRating, accommodation.maxNumberOfPeople, accommodation.room.bedRooms,
                                accommodation.room.beds, accommodation.room.bathRooms, accommodation.mainImageUrl))
                .from(accommodation)
                .leftJoin(accommodation.member, member)
                .where(accommodation.accommodationId.eq(id))
                .fetchOne();
        return Optional.ofNullable(accommodationDetail);
    }

    public List<AccommodationOptionLine> findAccommodationOptionLinesByAccommodation(Long id) {
        return queryFactory.selectFrom(accommodationOptionLine)
                .join(accommodationOptionLine.accommodation, accommodation)
                .on(accommodation.accommodationId.eq(id))
                .fetch();
    }

    public Slice<AccommodationRelatedCityResponse> findByAccommodationsByCityId(Long cityId, Pageable pageable) {
        List<AccommodationRelatedCityResponse> result = queryFactory.select(Projections.fields(AccommodationRelatedCityResponse.class,
                        accommodation.accommodationId, accommodation.name.as("roomName"),
                        accommodation.address.homeAddress.as("address"), accommodation.accommodationType,
                        accommodation.averageRating, accommodation.price.as("oneDayPerPrice"),
                        accommodation.commentCount.as("commentCount"),
                        accommodation.mainImageUrl.as("image")))
                .from(accommodation)
                .join(accommodation.city, city)
                .on(city.cityId.eq(cityId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + NEXT)
                .fetch();

        boolean hasNext = getHasNext(pageable, result);

        return new SliceImpl<>(result, pageable, hasNext);
    }

    public Slice<AccommodationSearchResponse> findAccommodationsByCondition(
            AccommodationSearchCondition searchCondition, List<Long> accommodationIds, Pageable pageable) {

        int guestNumber = searchCondition.getAdults() + searchCondition.getChildren();

        List<AccommodationSearchResponse> result = queryFactory.select(Projections.fields(AccommodationSearchResponse.class,
                        accommodation.accommodationId, accommodation.mainImageUrl.as("imageUrl"),
                        accommodation.averageRating, accommodation.commentCount,
                        accommodation.name.as("roomName"), accommodation.price.as("oneDayPerPrice")))
                .from(accommodation)
                .where(accommodation.accommodationId.in(accommodationIds),
                        accommodation.price.between(searchCondition.getMinPrice(), searchCondition.getMaxPrice()),
                        accommodation.maxNumberOfPeople.goe(guestNumber)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + NEXT)
                .fetch();

        boolean hasNext = getHasNext(pageable, result);
        return new SliceImpl<>(result, pageable, hasNext);
    }

    public List<SearchPriceResponse> findAccommodationPricesById(Set<Long> accommodations) {
        return queryFactory.select(Projections.fields(SearchPriceResponse.class,
                        accommodation.accommodationId, accommodation.price))
                .from(accommodation)
                .where(accommodation.accommodationId.in(accommodations))
                .orderBy(accommodation.price.asc())
                .fetch();
    }
}
