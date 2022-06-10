package com.example.airbnb.common.configuration.oauth;


import com.example.airbnb.business.core.domain.member.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Permission {

    Role role() default Role.NORMAL;

}
