package com.example.airbnb.business.web.controller.accommodation;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class AccommodationSearchResponse {
      private Long accommodationId;
      private String imageUrl;
      private Double averageRating;
      private Integer commentsNumber;
      private String name;
      private BigDecimal oneDayPerPrice;

}
