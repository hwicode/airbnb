package com.example.airbnb.business.web.service.accommodation;

import com.example.airbnb.business.core.domain.accommodation.*;
import com.example.airbnb.business.core.repository.accommodation.CityRepository;
import com.example.airbnb.business.core.repository.accommodation.querydsl.AccommodationReadRepository;
import com.example.airbnb.business.core.repository.accommodation.querydsl.AmenityReadRepository;
import com.example.airbnb.business.core.repository.accommodation.querydsl.CommentReadRepository;
import com.example.airbnb.business.core.repository.accommodation.querydsl.ImageReadRepository;
import com.example.airbnb.business.web.controller.accommodation.dto.*;
import com.example.airbnb.common.exception.BusinessException;
import com.example.airbnb.common.exception.accommodation.AccommodationTypeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccommodationReadService {

    private final AccommodationReadRepository accommodationReadRepository;
    private final ImageReadRepository imageReadRepository;
    private final AmenityReadRepository amenityReadRepository;
    private final CommentReadRepository commentReadRepository;
    private final CityRepository cityRepository;
    private final TagReadRepository tagReadRepository;

    @Transactional(readOnly = true)
    public AccommodationResponse findByAccommodationId(Long id) {
        AccommodationResponse accommodationResponse = accommodationReadRepository.findAccommodationById(id)
                .orElseThrow(() -> new BusinessException(AccommodationTypeException.ACCOMMODATION_NOT_FOUND));

        List<Image> images = imageReadRepository.findImagesByAccommodation(id);
        List<Amenity> amenities = amenityReadRepository.findAmenityCategoriesByAccommodation(id);
        List<Comment> comments = commentReadRepository.findCommentsByAccommodation(id);
        List<AccommodationOptionLine> accommodationOptionLines = accommodationReadRepository.findAccommodationOptionLinesByAccommodation(id);

        accommodationResponse.add(images, amenities, comments, accommodationOptionLines);
        log.info("숙소 상세조회: {}", accommodationResponse.getAccommodationId());
        return accommodationResponse;
    }

    @Transactional(readOnly = true)
    public List<AccommodationRelatedCityResponse> findByAccommodationsByCityName(String cityName) {
        Optional<City> findCity = cityRepository.findCityByName(cityName);
        if (findCity.isPresent()) {
            List<AccommodationRelatedCityResponse> accommodations = accommodationReadRepository.findByAccommodationsByCityId(findCity.get().getCityId());
            log.info("도시 이름으로 숙소 검색. 검색된 도시 {}", cityName);
            return accommodations;
        }
        log.info("도시 이름으로 숙소 검색. 검색된 도시 {}, 빈 숙소 반환", cityName);
        return Collections.emptyList();
    }

    @Transactional(readOnly = true)
    public List<SearchPriceResponse> findAccommodationPriceRangeByTagAndPeriod(String tag, LocalDate checkIn, LocalDate checkOut) {
        Set<Long> accommodations = tagReadRepository.getAccommodationsByTag(tag);
        return tagReadRepository.findAccommodationPriceRangeByTagAndPeriod(accommodations, checkIn, checkOut);
    }

    @Transactional(readOnly = true)
    public List<AccommodationSearchResponse> searchAccommodations(AccommodationSearchCondition searchCondition) {
        Set<Long> accommodations = tagReadRepository.getAccommodationsByTag(searchCondition.getTagName());

        List<SearchPriceResponse> searchPriceResponses = tagReadRepository
                .findAccommodationPriceRangeByTagAndPeriod(accommodations, searchCondition.getCheckIn(), searchCondition.getCheckOut());

        List<Long> accommodationIds = searchPriceResponses.stream()
                .map(SearchPriceResponse::getAccommodationId)
                .collect(Collectors.toList());

        return accommodationReadRepository.findAccommodationsByCondition(searchCondition, accommodationIds);
    }
}
