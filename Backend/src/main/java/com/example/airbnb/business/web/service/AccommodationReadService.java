package com.example.airbnb.business.web.service;

import com.example.airbnb.business.core.domain.accommodation.Accommodation;
import com.example.airbnb.business.core.repository.accommodation.AccommodationRepository;
import com.example.airbnb.business.core.repository.accommodation.querydsl.AccommodationReadRepository;
import com.example.airbnb.common.BusinessException;
import com.example.airbnb.common.exception.accommodation.AccommodationTypeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccommodationReadService {

    private final AccommodationRepository accommodationRepository;
    private final AccommodationReadRepository accommodationReadRepository;

    @Transactional(readOnly = true)
    public Accommodation findByAccommodationId(Long id) {
        return accommodationReadRepository.findAccommodationBy(id)
                .orElseThrow(() -> new BusinessException(AccommodationTypeException.ACCOMMODATION_NOT_FOUND));
    }
}
