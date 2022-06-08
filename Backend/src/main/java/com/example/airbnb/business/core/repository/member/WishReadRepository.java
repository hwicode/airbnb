package com.example.airbnb.business.core.repository.member;

import com.example.airbnb.business.web.controller.member.dto.WishResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.airbnb.business.core.domain.accommodation.QAccommodation.accommodation;
import static com.example.airbnb.business.core.domain.member.QMember.member;
import static com.example.airbnb.business.core.domain.member.QWish.wish;


@Repository
@RequiredArgsConstructor
public class WishReadRepository {

    private final JPAQueryFactory queryFactory;

    public List<WishResponse> findWishesByMemberId(Long id) {
        return queryFactory.select(
                        Projections.fields(WishResponse.class,
                                accommodation.accommodationId, accommodation.name.as("roomName"),
                                accommodation.mainImageUrl.as("image"),
                                accommodation.commentCount.as("commentCount"),
                                accommodation.averageRating, accommodation.price.as("oneDayPerPrice")))
                .from(wish)
                .join(wish.member, member)
                .on(member.memberId.eq(id))
                .join(wish.accommodation, accommodation)
                .fetch();
    }
}
