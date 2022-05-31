package com.example.airbnb.business.web.service.reservation;

import com.example.airbnb.business.core.domain.accommodation.Image;
import com.example.airbnb.business.core.domain.member.Member;
import com.example.airbnb.business.core.repository.accommodation.querydsl.ImageReadRepository;
import com.example.airbnb.business.core.repository.member.MemberRepository;
import com.example.airbnb.business.core.repository.reservation.querydsl.ReservationReadRepository;
import com.example.airbnb.business.web.controller.reservation.dto.DetailedReservationResponse;
import com.example.airbnb.business.web.controller.reservation.dto.ReservationListResponse;
import com.example.airbnb.business.web.controller.reservation.dto.ReservationResponse;
import com.example.airbnb.common.exception.BusinessException;
import com.example.airbnb.common.exception.member.MemberTypeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationReadService {

    private final ReservationReadRepository reservationReadRepository;
    private final ImageReadRepository imageReadRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public DetailedReservationResponse findByReservationId(Long id) {
        // todo: 추후에 예외처리 로직으로 수정하기
        DetailedReservationResponse detailedReservationResponse = reservationReadRepository.findReservationById(id)
                .orElseThrow(() -> new IllegalStateException("예약을 찾을 수 없습니다."));

        List<Image> images = imageReadRepository.findImagesByAccommodation(detailedReservationResponse.getAccommodationId());

        detailedReservationResponse.addImages(images);
        return detailedReservationResponse;
    }

    @Transactional(readOnly = true)
    public ReservationListResponse findReservations(String githubId) {
        Member member = memberRepository.findByGithubId(githubId)
                .orElseThrow(() -> new BusinessException(MemberTypeException.MEMBER_NOT_FOUND));

        List<ReservationResponse> reservationResponses = reservationReadRepository.findReservationsByMemberId(member.getMemberId());
        return new ReservationListResponse(reservationResponses);
    }
}
