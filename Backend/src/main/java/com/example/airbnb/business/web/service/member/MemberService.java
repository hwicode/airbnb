package com.example.airbnb.business.web.service.member;

import com.example.airbnb.business.core.domain.accommodation.Accommodation;
import com.example.airbnb.business.core.domain.member.Member;
import com.example.airbnb.business.core.domain.member.Wish;
import com.example.airbnb.business.core.repository.accommodation.AccommodationRepository;
import com.example.airbnb.business.core.repository.member.MemberRepository;
import com.example.airbnb.business.web.controller.member.dto.WishAddRequest;
import com.example.airbnb.common.exception.BusinessException;
import com.example.airbnb.common.exception.accommodation.AccommodationTypeException;
import com.example.airbnb.common.exception.member.MemberTypeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final AccommodationRepository accommodationRepository;
    private final WishRepository wishRepository;

    @Transactional
    public Wish addWish(String githubId, WishAddRequest wishAddRequest) {
        Member member = memberRepository.findByGithubId(githubId)
                .orElseThrow(() -> new BusinessException(MemberTypeException.MEMBER_NOT_FOUND));

        Accommodation accommodation = accommodationRepository.findByAccommodationId(wishAddRequest.getAccommodationId())
                .orElseThrow(() -> new BusinessException(AccommodationTypeException.ACCOMMODATION_NOT_FOUND));

        return wishRepository.save(new Wish(member, accommodation));
    }
}
