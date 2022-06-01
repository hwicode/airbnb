package com.example.airbnb.business.web.controller.member;

import com.example.airbnb.business.web.controller.member.dto.MemberWishListResponse;
import com.example.airbnb.business.web.controller.member.dto.WishResponse;
import com.example.airbnb.business.web.service.member.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberReadService memberReadService;

    @GetMapping("/{githubId}")
    public List<WishResponse> searchMemberWishList(@PathVariable String githubId) {
        return memberReadService.findWishes(githubId);
    }
}
