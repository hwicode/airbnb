package com.example.airbnb.business.core.repository.accommodation.querydsl;

import com.example.airbnb.business.core.domain.accommodation.Accommodation;
import com.example.airbnb.business.core.domain.accommodation.AccommodationType;
import com.example.airbnb.business.core.domain.accommodation.Address;
import com.example.airbnb.business.core.domain.accommodation.Image;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.airbnb.business.core.domain.accommodation.QAccommodation.accommodation;
import static com.example.airbnb.business.core.domain.accommodation.QImage.image;
import static com.example.airbnb.business.core.domain.member.QMember.member;

@Repository
@RequiredArgsConstructor
public class AccommodationReadRepository {

    @PersistenceContext
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    public Optional<Object> findAccommodationBy(Long id) {
        return null;
    }
}
