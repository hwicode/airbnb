package com.example.airbnb.business.web.controller.accommodation.dto;

import com.example.airbnb.business.core.domain.accommodation.Accommodation;
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

      public AccommodationSearchResponse(Accommodation accommodation) {
            this.accommodationId = accommodation.getAccommodationId();
            this.imageUrl = accommodation.getMainImageUrl();
            this.averageRating = accommodation.getAverageRating();
            this.commentsNumber = accommodation.getCommentCount();
            this.roomName = accommodation.getName();
            this.oneDayPerPrice = accommodation.getPrice();
      }
}
