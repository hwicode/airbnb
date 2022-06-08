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
import com.example.airbnb.common.exception.accommodation.CityTypeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        return accommodationResponse;
    }

    @Transactional(readOnly = true)
    public List<AccommodationRelatedCityResponse> findByAccommodationsByCityName(String cityName) {
        City city = cityRepository.findCityByName(cityName)
                .orElseThrow(() -> new BusinessException(CityTypeException.CITY_NOT_FOUND));
        return accommodationReadRepository.findByAccommodationsByCityId(city.getCityId());
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
