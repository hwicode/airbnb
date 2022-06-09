package com.example.airbnb.business.web.controller.accommodation.dto;

import com.example.airbnb.business.core.domain.accommodation.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Getter
public class AccommodationResponse {

    private Long accommodationId;
    private Long hostId;
    private String hostName;
    private String hostProfileImage;
    private String name;
    private String description;
    private BigDecimal oneDayPerPrice;
    private String address;
    private AccommodationType accommodationType;
    private Double averageRating;
    private String mainImageUrl;
    private List<String> images;
    private List<CommentResponse> comments;
    private Map<AmenityType, List<AmenityCategory>> amenityCategories;
    private List<AccommodationOptionLineResponse> accommodationOptionLines;
    private int commentSize;
    private int maxNumberOfPeople;
    private int bedRooms;
    private int beds;
    private int bathRooms;

    public AccommodationResponse() {
    }

    public void add(List<Image> images, List<Amenity> amenities, List<Comment> comments, List<AccommodationOptionLine> accommodationOptionLines) {
        addImages(images);
        amenities(amenities);
        addComments(comments);
        addAccommodationOptionLines(accommodationOptionLines);
    }

    public void addImages(List<Image> images) {
        if (images.size() > 0)
            this.images = images.stream()
                    .map(Image::getImageUrl)
                    .collect(Collectors.toList());
    }

    public void amenities(List<Amenity> amenities) {
        if (amenities.size() > 0)
            this.amenityCategories = createAmenityCategories(amenities);
    }

    private Map<AmenityType, List<AmenityCategory>> createAmenityCategories(List<Amenity> amenities) {
        return amenities.stream()
                .map(Amenity::getAmenityCategory)
                .collect(groupingBy(AmenityCategory::getAmenityType));
    }

    public void addComments(List<Comment> comments) {
        if (comments.size() > 0) {
            this.comments = createComments(comments);
            this.commentSize = comments.size();
        }
    }

    private List<CommentResponse> createComments(List<Comment> comments) {
        List<CommentResponse> commentResponses = new ArrayList<>();

        for (Comment comment : comments) {
            CommentResponse response = new CommentResponse(
                    comment.getCommentId(), comment.getMember().getMemberId(),
                    comment.getMember().getName(), comment.getContent(),
                    comment.getMember().getProfileImage(), comment.getCreateAt());
            commentResponses.add(response);
        }
        return commentResponses;
    }

    private void addAccommodationOptionLines(List<AccommodationOptionLine> accommodationOptionLines) {
        if (accommodationOptionLines.size() > 0)
            this.accommodationOptionLines = createAccommodationOptionLine(accommodationOptionLines);
    }

    private List<AccommodationOptionLineResponse> createAccommodationOptionLine(List<AccommodationOptionLine> accommodationOptionLines) {
        return accommodationOptionLines.stream()
                .map(AccommodationOptionLineResponse::new)
                .collect(Collectors.toList());
    }
}
