package com.example.airbnb.common.configuration.oauth;

import com.example.airbnb.business.core.domain.member.Member;
import com.example.airbnb.business.core.domain.member.Role;
import com.example.airbnb.common.login.token.jwt.JwtTokenProvider;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class PermissionInterceptor implements HandlerInterceptor {

    // TODO. 의존성 문제 해결
    private JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        return true;
    }
}
