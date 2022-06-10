package com.example.airbnb.business.web.service.member;

import com.example.airbnb.business.core.domain.member.Member;
import com.example.airbnb.business.core.repository.accommodation.read.AccommodationReadRepository;
import com.example.airbnb.business.core.repository.member.MemberRepository;
import com.example.airbnb.business.core.repository.member.read.WishReadRepository;
import com.example.airbnb.business.web.controller.member.dto.WishResponse;
import com.example.airbnb.common.exception.BusinessException;
import com.example.airbnb.common.exception.member.MemberTypeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberReadService {

    private final MemberRepository memberRepository;
    private final WishReadRepository wishReadRepository;
    private final AccommodationReadRepository accommodationReadRepository;

    @Transactional(readOnly = true)
    public List<WishResponse> findWishes(String githubId) {
        Member member = memberRepository.findByGithubId(githubId)
                .orElseThrow(() -> new BusinessException(MemberTypeException.MEMBER_NOT_FOUND));

        return wishReadRepository.findWishesByMemberId(member.getMemberId());
    }
}
