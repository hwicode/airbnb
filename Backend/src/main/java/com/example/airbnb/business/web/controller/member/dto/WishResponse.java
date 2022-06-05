package com.example.airbnb.business.web.controller.member.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WishResponse {

    private Long memberId;
    private Long accommodationId;
    private double averageRating;
    private String roomName;
    private BigDecimal oneDayPerPrice;
    private String image;
    private int commentCount;

    public WishResponse() {}

    public WishResponse(Long memberId, Long accommodationId, double averageRating, String roomName, BigDecimal oneDayPerPrice, String image, int commentCount) {
        this.memberId = memberId;
        this.accommodationId = accommodationId;
        this.averageRating = averageRating;
        this.roomName = roomName;
        this.oneDayPerPrice = oneDayPerPrice;
        this.image = image;
        this.commentCount = commentCount;
    }
}
