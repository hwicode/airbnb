package com.example.airbnb.business.web.controller.accommodation.dto;

import com.example.airbnb.business.core.domain.accommodation.Comment;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Comments {

    private List<CommentResponse> comments;

    public Comments(List<Comment> comments) {
        this.comments = createAllComments(comments);
    }

    private List<CommentResponse> createAllComments(List<Comment> comments) {
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
}
