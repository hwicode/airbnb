package com.example.airbnb.business.web.controller.accommodation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {

    private Long commentId;
    private Long writerId;
    private String writer;
    private String content;
    private String profileImage;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createAt;

    public CommentResponse(Long commentId, Long writerId, String writer, String content, String profileImage, LocalDateTime createAt) {
        this.commentId = commentId;
        this.writerId = writerId;
        this.writer = writer;
        this.content = content;
        this.profileImage = profileImage;
        this.createAt = createAt;
    }
}
