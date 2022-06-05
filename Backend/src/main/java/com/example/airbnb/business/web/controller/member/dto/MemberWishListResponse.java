package com.example.airbnb.business.web.controller.member.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class MemberWishListResponse {

    private List<WishResponse> wishes;

    public MemberWishListResponse(List<WishResponse> wishes) {
        this.wishes = wishes;
    }
}
