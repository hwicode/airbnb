package com.example.airbnb.business.web.controller.accommodation.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class AccommodationSearchResponse {
      private Long accommodationId;
      private String imageUrl;
      private Double averageRating;
      private Integer commentsNumber;
      private String roomName;
      private BigDecimal oneDayPerPrice;

      public AccommodationSearchResponse(Long accommodationId, String imageUrl, Double averageRating, Integer commentsNumber, String roomName, BigDecimal oneDayPerPrice) {
            this.accommodationId = accommodationId;
            this.imageUrl = imageUrl;
            this.averageRating = averageRating;
            this.commentsNumber = commentsNumber;
            this.roomName = roomName;
            this.oneDayPerPrice = oneDayPerPrice;
      }
}
