package com.example.airbnb.business.core.domain.accommodation;

import com.example.airbnb.business.core.domain.BaseEntity;
import com.example.airbnb.business.core.domain.member.Member;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Getter
public class Comment extends BaseEntity {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @NotNull @NotEmpty
    private String content;

    @NotNull
    private Double rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    public Comment(String content, Double rating, Member member, Accommodation accommodation) {
        validate(member, accommodation);
        this.content = content;
        this.rating = rating;
        this.member = member;
        this.accommodation = accommodation;
    }

    protected Comment() {}

    private void validate(Member member, Accommodation accommodation) {
        if (member == null || accommodation == null) {
            throw new IllegalArgumentException("회원과 숙소 정보를 입력해주세요.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(commentId, comment.commentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId);
    }
}
