package com.example.airbnb.business.web.controller.accommodation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class AccommodationSearchCondition {

    private String address;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkIn;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkOut;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private int adults;
    private int children;
    private int infants;
    private Double latitude;
    private Double longitude;

    public AccommodationSearchCondition (){};

}
