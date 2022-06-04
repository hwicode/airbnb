package com.example.airbnb.configuration.data;

import com.example.airbnb.business.core.domain.member.Member;

public class MemberData {

    private Member member;

    public MemberData() {
        this.member = createMember();
    }

    private Member createMember() {
        return new Member("devjun10", "Jun", "https://a0.muscache.com/defaults/user_pic-225x225.png?v=3");
    }
}
