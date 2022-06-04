package com.example.airbnb.configuration;

import com.example.airbnb.business.core.repository.accommodation.querydsl.AccommodationReadRepository;
import com.example.airbnb.business.core.repository.accommodation.querydsl.AmenityReadRepository;
import com.example.airbnb.business.core.repository.accommodation.querydsl.CommentReadRepository;
import com.example.airbnb.business.core.repository.accommodation.querydsl.ImageReadRepository;
import com.example.airbnb.business.web.service.accommodation.TagReadRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@TestConfiguration
public class TestConfig {

    @PersistenceContext
    private EntityManager entityManager;


    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    @Bean
    public AccommodationReadRepository accommodationReadRepository(){
        return new AccommodationReadRepository(jpaQueryFactory());
    }

    @Bean
    public AmenityReadRepository amenityReadRepository(){
        return new AmenityReadRepository(jpaQueryFactory());
    }

    @Bean
    public ImageReadRepository imageReadRepository(){
        return new ImageReadRepository(jpaQueryFactory());
    }

    @Bean
    public CommentReadRepository commentReadRepository(){
        return new CommentReadRepository(jpaQueryFactory());
    }

    @Bean
    public TagReadRepository tagReadRepository(){
        return new TagReadRepository(jpaQueryFactory());
    }
}
