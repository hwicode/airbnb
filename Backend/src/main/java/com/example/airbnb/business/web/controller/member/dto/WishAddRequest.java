package com.example.airbnb.business.web.controller.member.dto;

import lombok.Getter;

@Getter
public class WishAddRequest {

    private String githubId;
    private Long accommodationId;

    public WishAddRequest() {}

}
