package com.example.airbnb.common.configuration.oauth;

import com.example.airbnb.common.login.token.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Component
public class OauthInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtTokenProvider provider;

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String token = Arrays.stream(request.getCookies()).map(x -> x.getName().equals("refreshToken")).toString();
//        System.out.println(token);
//        if (token != null) {
//            System.out.println("리다이렉트");
//            response.sendRedirect("/api/accommodations/1");
//            return true;
//        }
//        return true;
//    }
}
