package com.example.airbnb.business.web.service.accommodation;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TagReadRepository {

    private final JPAQueryFactory queryFactory;

}
