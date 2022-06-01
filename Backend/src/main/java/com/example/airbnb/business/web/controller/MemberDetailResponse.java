package com.example.airbnb.business.web.controller;

import com.example.airbnb.business.core.domain.member.Member;
import lombok.Getter;

@Getter
public class MemberDetailResponse {

    private Integer memberId;

    public MemberDetailResponse(Member member) {
        this.memberId = member.getMemberId();
    }
}
