package com.example.airbnb.business.web.controller.member;

import com.example.airbnb.business.core.domain.member.Wish;
import com.example.airbnb.business.web.controller.member.dto.WishAddRequest;
import com.example.airbnb.business.web.controller.member.dto.WishResponse;
import com.example.airbnb.business.web.service.member.MemberReadService;
import com.example.airbnb.business.web.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberReadService memberReadService;
    private final MemberService memberService;

    @GetMapping("/{githubId}")
    public List<WishResponse> searchMemberWishList(@PathVariable String githubId) {
        return memberReadService.findWishes(githubId);
    }

    @PostMapping("/wishes")
    public WishResponse addWish(@RequestBody WishAddRequest wishAddRequest) {
        Wish wish = memberService.addWish(wishAddRequest);
        return new WishResponse(wish);
    }
}
