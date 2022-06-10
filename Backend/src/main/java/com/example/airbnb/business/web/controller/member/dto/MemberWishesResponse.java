package com.example.airbnb.business.web.controller.member.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class MemberWishesResponse {

    private List<WishResponse> wishes;

    public MemberWishesResponse(List<WishResponse> wishes) {
        this.wishes = wishes;
    }
}
