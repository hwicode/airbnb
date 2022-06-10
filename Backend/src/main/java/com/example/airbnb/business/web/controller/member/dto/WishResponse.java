package com.example.airbnb.business.web.controller.member.dto;

import com.example.airbnb.business.core.domain.member.Wish;
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

    public WishResponse(Wish wish) {
        this.memberId = wish.getMember().getMemberId();
        this.accommodationId = wish.getAccommodation().getAccommodationId();
        this.averageRating = wish.getAccommodation().getAverageRating();
        this.roomName = wish.getAccommodation().getName();
        this.oneDayPerPrice = wish.getAccommodation().getPrice();
        this.image = wish.getAccommodation().getMainImageUrl();
        this.commentCount = wish.getAccommodation().getCommentCount();
    }
}
