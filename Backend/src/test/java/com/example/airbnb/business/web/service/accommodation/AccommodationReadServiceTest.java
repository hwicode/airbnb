package com.example.airbnb.business.web.service.accommodation;

import com.example.airbnb.business.core.domain.accommodation.Accommodation;
import com.example.airbnb.business.core.domain.accommodation.AccommodationOptionLine;
import com.example.airbnb.business.core.domain.accommodation.City;
import com.example.airbnb.business.core.domain.member.Member;
import com.example.airbnb.business.web.controller.accommodation.dto.AccommodationResponse;
import com.example.airbnb.common.exception.BusinessException;
import com.example.airbnb.configuration.TestConfig;
import com.example.airbnb.configuration.data.AccommodationData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.BDDAssertions.then;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({TestConfig.class, AccommodationReadService.class})
@ActiveProfiles("test")
@DisplayName("AccommodationReadService 통합 테스트")
class AccommodationReadServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccommodationReadService accommodationReadService;

    private Accommodation accommodation;
    private AccommodationOptionLine accommodationOptionLine;
    private Member member;
    private City city;

    @BeforeEach
    void setUp() {
        AccommodationData data = new AccommodationData();
        this.accommodation = data.getAccommodation();
        this.accommodationOptionLine = data.getAccommodationOptionLine();
        this.member = data.getMember();
        this.city = data.getCity();

        entityManager.persist(accommodationOptionLine);
        entityManager.persist(member);
        entityManager.persist(city);
        entityManager.persist(accommodation);
    }

    @Test
    @DisplayName("숙소를 PK로 조회했을때 해당 데이터가 있으면 이를 반환한다.")
    void findAccommodationById() {
        //when
        AccommodationResponse findAccommodation = accommodationReadService.findByAccommodationId(1L);

        // then
        then(findAccommodation.getAccommodationId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("숙소를 PK로 조회했을때 해당 데이터가 없다면 BusinessException이 발생한다.")
    void findAccommodationById_Exception() {

        // when, then
        Assertions.assertThrows(BusinessException.class,
                () -> accommodationReadService.findByAccommodationId(1000L));
    }
}
