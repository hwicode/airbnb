package com.example.airbnb.business.web.service.reservation;

import com.example.airbnb.business.core.domain.accommodation.Accommodation;
import com.example.airbnb.business.core.domain.accommodation.AccommodationTag;
import com.example.airbnb.business.core.domain.accommodation.City;
import com.example.airbnb.business.core.domain.accommodation.Tag;
import com.example.airbnb.business.core.domain.member.Member;
import com.example.airbnb.business.core.domain.reservation.Reservation;
import com.example.airbnb.business.core.repository.reservation.ReservationRepository;
import com.example.airbnb.business.core.repository.reservation.querydsl.ReservationReadRepository;
import com.example.airbnb.business.web.controller.accommodation.dto.SearchPriceResponse;
import com.example.airbnb.business.web.controller.reservation.dto.DetailedReservationResponse;
import com.example.airbnb.business.web.service.accommodation.AccommodationReadService;
import com.example.airbnb.common.exception.BusinessException;
import com.example.airbnb.common.exception.accommodation.AccommodationTypeException;
import com.example.airbnb.configuration.TestConfig;
import com.example.airbnb.configuration.data.AccommodationData;
import com.example.airbnb.configuration.data.ReservationData;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({TestConfig.class, ReservationReadService.class, AccommodationReadService.class})
@ActiveProfiles("test")
@DisplayName("Reservation 통합 테스트")
class ReservationReadServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ReservationReadRepository reservationReadRepository;

    @Autowired
    private AccommodationReadService accommodationReadService;

    @Autowired
    private ReservationRepository reservationRepository;

    private Accommodation accommodation;
    private AccommodationTag accommodationTag;
    private Member member;
    private City city;
    private Tag tag;
    private Reservation reservation;

    @BeforeEach
    void setUp() {
        AccommodationData accommodationData = new AccommodationData();
        ReservationData reservationData = new ReservationData();

        this.city = entityManager.persist(accommodationData.getCity());
        this.entityManager.persist(accommodationData.getAccommodationOptionLine());
        this.member = entityManager.persist(accommodationData.getMember());
        this.accommodation = entityManager.persist(accommodationData.getAccommodation());
        this.tag = entityManager.persist(accommodationData.getTag());
        this.accommodationTag = entityManager.persist(accommodationData.getAccommodationTag());

        Reservation reservation = reservationData.getReservation();
        reservation.addInformation(member, accommodation);
        this.reservation = reservationRepository.save(reservation);
    }

    @Test
    @DisplayName("숙소 예약을 PK로 조회했을때 해당 데이터가 있으면 이를 반환한다.")
    void findReservationById() {
        //when
        Optional<DetailedReservationResponse> findReservation = reservationReadRepository.findReservationById(1L);

        // then
        then(findReservation).isPresent();
    }

    @Test
    @DisplayName("숙소 예약을 PK로 조회했을때 해당 데이터가 없다면 예외가 발생한다.")
    void findReservationById_Exception() {
        Assertions.assertThrows(BusinessException.class, () -> reservationReadRepository.findReservationById(99999L)
                .orElseThrow(() -> new BusinessException(AccommodationTypeException.RESERVATION_NOT_FOUND)));
    }


    // 예약된 Reservation 2022.06.10 ~ 2022.06.15
    // 조회  Reservation 2022.06.16 ~ 2022.06.18
    @Test
    @DisplayName("특정 지역으로 검색했을때 체크인/체크아웃 기간에 해당되는 숙소가 있다면 해당 숙소들의 가격 리스트를 반환한다.")
    void findAccommodationPriceRangeByTagAndPeriod() {

        String tag = "제주도";
        LocalDate checkIn = LocalDate.of(2022, 6, 16);
        LocalDate checkOut = LocalDate.of(2022, 6, 18);

        List<SearchPriceResponse> priceRange = accommodationReadService
                .findAccommodationPriceRangeByTagAndPeriod(tag, checkIn, checkOut);

        then(priceRange.size()).isEqualTo(1);
    }

    // 등록된 키워드: 제주도
    // 검색 키워드 : 서울
    @Test
    @DisplayName("특정 지역으로 검색했을때 해당 키워드에 해당되는 숙소가 없다면 빈 리스트를 반환한다.")
    void findAccommodationPriceRangeByTagAndPeriod_Except() {

        String tag = "서울";
        LocalDate checkIn = LocalDate.of(2022, 6, 15);
        LocalDate checkOut = LocalDate.of(2022, 6, 18);

        List<SearchPriceResponse> priceRange = accommodationReadService
                .findAccommodationPriceRangeByTagAndPeriod(tag, checkIn, checkOut);

        then(priceRange.size()).isEqualTo(0);
    }

    // 예약된 Reservation 2022.06.09 ~ 2022.06.15
    // 조회  Reservation 2022.06.16 ~ 2022.06.18
    @Test
    @DisplayName("특정 지역으로 검색했을때 체크인/체크아웃 기간에 해당되는 숙소가 없다면 빈 리스트를 반환한다.")
    void findAccommodationPriceRangeByTagAndPeriod_Except2() {

        String tag = "제주도";
        LocalDate checkIn = LocalDate.of(2022, 6, 9);
        LocalDate checkOut = LocalDate.of(2022, 6, 18);

        List<SearchPriceResponse> priceRange = accommodationReadService
                .findAccommodationPriceRangeByTagAndPeriod(tag, checkIn, checkOut);

        then(priceRange.size()).isEqualTo(0);
    }
}
