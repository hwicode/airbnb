package com.example.airbnb.business.core.repository.accommodation.querydsl;

import com.example.airbnb.business.core.domain.accommodation.AccommodationOptionLine;
import com.example.airbnb.business.core.domain.accommodation.Location;
import com.example.airbnb.business.web.controller.accommodation.dto.AccommodationSearchCondition;
import com.example.airbnb.business.web.controller.accommodation.dto.AccommodationSearchResponse;
import com.example.airbnb.business.web.controller.accommodation.dto.SearchPriceResponse;
import com.example.airbnb.business.web.controller.accommodation.dto.AccommodationRelatedCityResponse;
import com.example.airbnb.business.web.controller.accommodation.dto.AccommodationResponse;
import com.example.airbnb.common.geometry.objects.Position;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.airbnb.business.core.domain.accommodation.QAccommodation.accommodation;
import static com.example.airbnb.business.core.domain.accommodation.QAccommodationOptionLine.accommodationOptionLine;
import static com.example.airbnb.business.core.domain.accommodation.QCity.city;
import static com.example.airbnb.business.core.domain.member.QMember.member;

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
                                accommodation.room.beds, accommodation.room.bathRooms))
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

    public List<SearchPriceResponse> findAccommodationPriceRangeBy() {
        return null;
    }

    public Position cal(double lng, double log) {
        return null;
    }

    public List<AccommodationRelatedCityResponse> findByAccommodationsByCityId(Long cityId) {
        return queryFactory.select(Projections.fields(AccommodationRelatedCityResponse.class,
                                accommodation.accommodationId, accommodation.name.as("roomName"),
                                accommodation.address.homeAddress.as("address"), accommodation.accommodationType,
                                accommodation.averageRating, accommodation.price.as("oneDayPerPrice"),
                                accommodation.commentCount.as("commentCount"),
                                accommodation.mainImageUrl.as("image")))
                .from(accommodation)
                .join(accommodation.city, city)
                .on(city.cityId.eq(cityId))
                .fetch();
    }

    public List<AccommodationSearchResponse> findAccommodationsByCondition(AccommodationSearchCondition searchCondition
            , List<Long> accommodationIds) {

        int guestNumber = searchCondition.getAdults() + searchCondition.getChildren();

        return queryFactory.select(Projections.fields(AccommodationSearchResponse.class,
                        accommodation.accommodationId, accommodation.mainImageUrl.as("imageUrl"),
                        accommodation.averageRating, accommodation.commentCount,
                        accommodation.name.as("roomName"), accommodation.price.as("oneDayPerPrice")))
                .from(accommodation)
                .where(accommodation.accommodationId.in(accommodationIds))
                .where(accommodation.price.between(searchCondition.getMinPrice(), searchCondition.getMaxPrice()))
                .where(accommodation.maxNumberOfPeople.goe(guestNumber))
                .fetch();
    }

}
