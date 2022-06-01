package com.example.airbnb.business.web.controller.member.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class MemberWishResponse {

    private Long memberId;
    private Long accommodationId;
    private double averageRating;
    private String roomName;
    private BigDecimal oneDayPerPrice;

    private String image;
    private int commentNumber;

    public MemberWishResponse() {

    }
}
