package com.example.airbnb.business.web.service.accommodation;

import com.example.airbnb.business.core.domain.accommodation.Accommodation;
import com.example.airbnb.business.core.domain.accommodation.City;
import com.example.airbnb.business.core.domain.member.Member;
import com.example.airbnb.business.core.repository.accommodation.AccommodationRepository;
import com.example.airbnb.business.core.repository.accommodation.CityRepository;
import com.example.airbnb.business.core.repository.member.MemberRepository;
import com.example.airbnb.business.web.controller.accommodation.dto.AccommodationRegisterRequest;
import com.example.airbnb.common.exception.BusinessException;
import com.example.airbnb.common.exception.accommodation.CityTypeException;
import com.example.airbnb.common.exception.member.MemberTypeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final MemberRepository memberRepository;
    private final CityRepository cityRepository;

    @Transactional
    public Accommodation registerAccommodation(AccommodationRegisterRequest request) {

        Member member = memberRepository.findByGithubId(request.getGithubId())
                .orElseThrow(() -> new BusinessException(MemberTypeException.MEMBER_NOT_FOUND));

        City city = cityRepository.findCityByName(request.getCityName())
                .orElseThrow(() -> new BusinessException(CityTypeException.CITY_NOT_FOUND));

        Accommodation accommodation = request.toEntity();
        accommodation.addInformation(member, city);

        return accommodationRepository.save(accommodation);
    }
}
