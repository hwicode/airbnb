package com.example.airbnb.business.web.controller.member.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class MemberWishListResponse {

    private List<MemberWishResponse> wishes;

    public MemberWishListResponse(List<MemberWishResponse> wishes) {
        this.wishes = wishes;
    }
}
