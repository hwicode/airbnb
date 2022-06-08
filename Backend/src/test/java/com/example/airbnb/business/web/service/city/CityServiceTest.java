package com.example.airbnb.business.web.service.city;

import com.example.airbnb.business.core.domain.accommodation.City;
import com.example.airbnb.business.core.repository.accommodation.CityRepository;
import com.example.airbnb.configuration.TestConfig;
import com.example.airbnb.configuration.data.CityData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({TestConfig.class, CityService.class})
@ActiveProfiles("test")
@DisplayName("CityService 통합 테스트")
class CityServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CityRepository cityRepository;

    private List<City> cities;

    @BeforeEach
    void setUp(){
        CityData cityData = new CityData();
        this.cities = cityData.getCities();
        this.cities.forEach(entity->entityManager.persist(entity));
    }

    @Test
    @DisplayName("도시 목록을 조회하면 등록된 모든 도시가 반환된다.")
    void findCities() {
        // when
        List<City> findCities = cityRepository.findAll();

        // then
        then(findCities.size()).isEqualTo(6);
    }

    @Test
    @DisplayName("도시 목록을 조회했을때 결과가 없다면 빈 리스트가 반환된다.")
    void findCities_Empty() {
        // given
        cityRepository.deleteAll();
        cityRepository.flush();

        // when
        List<City> findCities = cityRepository.findAll();

        // then
        then(findCities.size()).isEqualTo(0);
    }
}
