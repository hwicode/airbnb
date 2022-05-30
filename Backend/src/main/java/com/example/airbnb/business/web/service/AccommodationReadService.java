package com.example.airbnb.business.web.service;

import com.example.airbnb.business.core.domain.accommodation.AccommodationOptionLine;
import com.example.airbnb.business.core.domain.accommodation.AmenitySubCategory;
import com.example.airbnb.business.core.domain.accommodation.Comment;
import com.example.airbnb.business.core.domain.accommodation.Image;
import com.example.airbnb.business.core.repository.accommodation.AccommodationRepository;
import com.example.airbnb.business.core.repository.accommodation.querydsl.AccommodationReadRepository;
import com.example.airbnb.business.web.controller.accommodation.dto.AccommodationResponse;
import com.example.airbnb.common.BusinessException;
import com.example.airbnb.common.exception.accommodation.AccommodationTypeException;
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

    @Transactional(readOnly = true)
    public AccommodationResponse findByAccommodationId(Long id) {
        AccommodationResponse accommodationResponse = accommodationReadRepository.findAccommodationById(id)
                .orElseThrow(() -> new BusinessException(AccommodationTypeException.ACCOMMODATION_NOT_FOUND));
        List<Image> images = imageReadRepository.findImageByAccommodation(id);
        List<AmenitySubCategory> amenitySubCategories = amenityReadRepository.findAmenityCategoryByAccommodation(id);
        List<Comment> comments = commentReadRepository.findCommentByAccommodation(id);
        List<AccommodationOptionLine> accommodationOptionLines = accommodationReadRepository.findAccommodationOptionLineByAccommodation(id);

        accommodationResponse.add(images, amenitySubCategories, comments, accommodationOptionLines);
        return accommodationResponse;
    }
}
