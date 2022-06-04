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
import com.example.airbnb.common.geometry.objects.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        Optional<City> findCity = cityRepository.findCityByName(cityName);
        if(findCity.isPresent()){
            return accommodationReadRepository.findByAccommodationsByCityId(findCity.get().getCityId());
        }
        return Collections.emptyList();
    }

    @Transactional(readOnly = true)
    public List<SearchPriceResponse> findAccommodationPriceRangeByTagAndPeriod(String tag, LocalDate checkIn, LocalDate checkOut) {
        return tagReadRepository.findAccommodationPriceRangeByTagAndPeriod(tag, checkIn, checkOut);
    }

    public Position cal(double lng, double log) {
        return accommodationReadRepository.cal(lng, log);
    }

    @Transactional(readOnly = true)
    public List<AccommodationSearchResponse> searchAccommodations(AccommodationSearchCondition searchCondition) {
        return accommodationReadRepository.findAccommodationsByCondition(searchCondition);
    }
}
