package com.example.airbnb.business.web.controller.member;

import com.example.airbnb.business.core.domain.member.Role;
import com.example.airbnb.business.core.domain.member.Wish;
import com.example.airbnb.business.web.controller.member.dto.WishAddRequest;
import com.example.airbnb.business.web.controller.member.dto.WishResponse;
import com.example.airbnb.business.web.service.member.MemberReadService;
import com.example.airbnb.business.web.service.member.MemberService;
import com.example.airbnb.common.configuration.oauth.Permission;
import com.example.airbnb.common.login.token.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberReadService memberReadService;
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping
    public List<WishResponse> searchMemberWishList(HttpServletRequest httpServletRequest) {
        String githubId = jwtTokenProvider.getAttribute(httpServletRequest.getHeader("Authorization"));
        return memberReadService.findWishes(githubId);
    }

    @PostMapping("/wishes")
    public WishResponse addWish(HttpServletRequest httpServletRequest, @RequestBody WishAddRequest wishAddRequest) {
        String githubId = jwtTokenProvider.getAttribute(httpServletRequest.getHeader("Authorization"));
        Wish wish = memberService.addWish(githubId, wishAddRequest);
        return new WishResponse(wish);
    }

    @Permission(role = Role.ADMIN)
    @PostMapping
    public RoleChangeResponse changeRole(){
        return null;
    }
}
