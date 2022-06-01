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

    public WishResponse() {

    }
}
