package com.example.airbnb.business.web.service.reservation;

import com.example.airbnb.business.core.domain.accommodation.Image;
import com.example.airbnb.business.core.repository.accommodation.querydsl.ImageReadRepository;
import com.example.airbnb.business.core.repository.reservation.querydsl.ReservationReadRepository;
import com.example.airbnb.business.web.controller.reservation.dto.ReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationReadService {

    private final ReservationReadRepository reservationReadRepository;
    private final ImageReadRepository imageReadRepository;

    @Transactional(readOnly = true)
    public ReservationResponse findByReservationId(Long id) {
        // todo: 추후에 예외처리 로직으로 수정하기
        ReservationResponse reservationResponse = reservationReadRepository.findReservationById(id)
                .orElseThrow(() -> new IllegalStateException("예약을 찾을 수 없습니다."));

        List<Image> images = imageReadRepository.findImagesByAccommodation(reservationResponse.getAccommodationId());

        reservationResponse.addImages(images);
        return reservationResponse;
    }
}
