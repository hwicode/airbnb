package com.example.airbnb.business.web.service.accommodation;

import com.example.airbnb.business.core.domain.accommodation.*;
import com.example.airbnb.business.core.repository.accommodation.CityRepository;
import com.example.airbnb.business.core.repository.accommodation.querydsl.AccommodationReadRepository;
import com.example.airbnb.business.core.repository.accommodation.querydsl.AmenityReadRepository;
import com.example.airbnb.business.core.repository.accommodation.querydsl.CommentReadRepository;
import com.example.airbnb.business.core.repository.accommodation.querydsl.ImageReadRepository;
import com.example.airbnb.business.web.controller.accommodation.AccommodationSearchCondition;
import com.example.airbnb.business.web.controller.accommodation.AccommodationSearchResponse;
import com.example.airbnb.business.web.controller.accommodation.SearchPriceResponse;
import com.example.airbnb.business.web.controller.accommodation.dto.AccommodationRelatedCityResponse;
import com.example.airbnb.business.web.controller.accommodation.dto.AccommodationResponse;
import com.example.airbnb.common.exception.BusinessException;
import com.example.airbnb.common.exception.accommodation.AccommodationTypeException;
import com.example.airbnb.common.exception.accommodation.CityTypeException;
import com.example.airbnb.common.geometry.objects.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        List<AmenityCategory> amenitySubCategories = amenityReadRepository.findAmenityCategoriesByAccommodation(id);
        List<Comment> comments = commentReadRepository.findCommentsByAccommodation(id);
        List<AccommodationOptionLine> accommodationOptionLines = accommodationReadRepository.findAccommodationOptionLinesByAccommodation(id);

        accommodationResponse.add(images, amenitySubCategories, comments, accommodationOptionLines);
        return accommodationResponse;
    }

    @Transactional(readOnly = true)
    public List<AccommodationRelatedCityResponse> findByAccommodationsByCityName(String cityName) {
        City city = cityRepository.findCityByName(cityName)
                .orElseThrow(() -> new BusinessException(CityTypeException.CITY_NOT_FOUND));
        return accommodationReadRepository.findByAccommodationsByCityId(city.getCityId());
    }

    @Transactional(readOnly = true)
    public List<SearchPriceResponse> findAccommodationPriceRangeBy(String tag) {
        return tagReadRepository.findAccommodationTagBy(tag);
    }

    public Position cal(double lng, double log) {
        return accommodationReadRepository.cal(lng, log);
    }

    @Transactional(readOnly = true)
    public List<AccommodationSearchResponse> searchAccommodations(AccommodationSearchCondition searchCondition) {
        return null;
    }
}