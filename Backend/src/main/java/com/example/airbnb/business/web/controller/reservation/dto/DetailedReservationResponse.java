package com.example.airbnb.business.web.controller.reservation.dto;

import com.example.airbnb.business.core.domain.accommodation.Image;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class DetailedReservationResponse {

    private Long reservationId;
    private Long accommodationId;
    private String roomName;
    private String address;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private Long hostId;
    private String hostName;
    private int totalPeople;
    private int totalDay;
    private BigDecimal totalPrice;

    private List<String> images;

    public DetailedReservationResponse() {
    }

    public void addImages(List<Image> images) {
        if (images.size() > 0)
            this.images = images.stream()
                    .map(Image::getImageUrl)
                    .collect(Collectors.toList());
    }

}
