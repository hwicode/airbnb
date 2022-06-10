package com.example.airbnb.common.configuration.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        PermissionRequiredUrls permissionRequiredUrls = new PermissionRequiredUrls();

//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/api/**");
//
//        registry.addInterceptor(new PermissionInterceptor())
//                .addPathPatterns(permissionRequiredUrls.getUrls())
//                .excludePathPatterns();


    }
}
