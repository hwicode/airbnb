package com.example.airbnb.business.web.service.accommodation;

import com.example.airbnb.business.core.domain.accommodation.*;
import com.example.airbnb.business.core.repository.city.CityRepository;
import com.example.airbnb.business.core.repository.accommodation.read.AccommodationReadRepository;
import com.example.airbnb.business.core.repository.accommodation.read.AmenityReadRepository;
import com.example.airbnb.business.core.repository.accommodation.read.CommentReadRepository;
import com.example.airbnb.business.core.repository.accommodation.read.ImageReadRepository;
import com.example.airbnb.business.web.controller.accommodation.dto.*;
import com.example.airbnb.common.exception.BusinessException;
import com.example.airbnb.common.exception.accommodation.AccommodationTypeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.airbnb.common.utils.PageUtils.PAGE_SIZE;
import static java.lang.Boolean.FALSE;
import static java.util.Collections.EMPTY_LIST;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccommodationReadService {

    private final CityRepository cityRepository;

    private final AccommodationReadRepository accommodationReadRepository;
    private final ImageReadRepository imageReadRepository;
    private final AmenityReadRepository amenityReadRepository;
    private final CommentReadRepository commentReadRepository;
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
    public Slice<AccommodationRelatedCityResponse> findByAccommodationsByCityName(String cityName, int page) {
        Optional<City> findCity = cityRepository.findCityByName(cityName);
        if (findCity.isPresent()) {
            Slice<AccommodationRelatedCityResponse> accommodations = accommodationReadRepository.findByAccommodationsByCityId(findCity.get().getCityId(), PageRequest.of(page, 10));
            log.info("도시 이름으로 숙소 검색. 검색된 도시 {}", cityName);
            return accommodations;
        }
        return new SliceImpl<AccommodationRelatedCityResponse>(EMPTY_LIST, PageRequest.of(0, 1), FALSE);
    }

    @Transactional(readOnly = true)
    public List<SearchPriceResponse> findAccommodationPriceRangeByTagAndPeriod(String tag, LocalDate checkIn, LocalDate checkOut) {
        if (checkIn == null || checkOut == null) {
            Set<Long> accommodations = tagReadRepository.getAccommodationsByTag(tag);
            return accommodationReadRepository.findAccommodationPricesById(accommodations);
        }
        Set<Long> accommodations = tagReadRepository.getAccommodationsByTag(tag);
        return tagReadRepository.findAccommodationPriceRangeByTagAndPeriod(accommodations, checkIn, checkOut);
    }

    @Transactional(readOnly = true)
    public Slice<AccommodationSearchResponse> searchAccommodations(AccommodationSearchCondition searchCondition, int page) {
        String tag = searchCondition.getTagName();
        LocalDate checkIn = searchCondition.getCheckIn();
        LocalDate checkOut = searchCondition.getCheckOut();

        List<Long> accommodationIds = findAccommodationPriceRangeByTagAndPeriod(tag, checkIn, checkOut).stream()
                .map(SearchPriceResponse::getAccommodationId)
                .collect(Collectors.toList());

        return accommodationReadRepository.findAccommodationsByCondition(searchCondition, accommodationIds, PageRequest.of(page, PAGE_SIZE));
    }
}
