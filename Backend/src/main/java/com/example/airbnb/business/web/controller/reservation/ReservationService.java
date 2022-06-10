package com.example.airbnb.business.web.controller.reservation;

import com.example.airbnb.business.core.domain.accommodation.Accommodation;
import com.example.airbnb.business.core.domain.member.Member;
import com.example.airbnb.business.core.domain.reservation.Reservation;
import com.example.airbnb.business.core.repository.accommodation.AccommodationRepository;
import com.example.airbnb.business.core.repository.member.MemberRepository;
import com.example.airbnb.business.core.repository.reservation.ReservationRepository;
import com.example.airbnb.business.web.controller.reservation.dto.AccommodationReservationRequest;
import com.example.airbnb.common.exception.BusinessException;
import com.example.airbnb.common.exception.accommodation.AccommodationTypeException;
import com.example.airbnb.common.exception.member.MemberTypeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {


    private final MemberRepository memberRepository;

    private final AccommodationRepository accommodationRepository;
    private final ReservationRepository reservationRepository;

    @Transactional
    public Reservation reservation(String githubId, Long accommodationId, AccommodationReservationRequest request) {
        Accommodation findAccommodation = accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new BusinessException(AccommodationTypeException.ACCOMMODATION_NOT_FOUND));

        Member findMember = memberRepository.findByGithubId(githubId)
                .orElseThrow(() -> new BusinessException(MemberTypeException.MEMBER_NOT_FOUND));
        Reservation reservation = request.toEntity();

        reservation.addInformation(findMember, findAccommodation);
        return reservationRepository.save(reservation);
    }
}
