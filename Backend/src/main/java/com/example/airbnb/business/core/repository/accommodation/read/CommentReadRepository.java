package com.example.airbnb.business.core.repository.accommodation.querydsl;

import com.example.airbnb.business.core.domain.accommodation.Comment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.airbnb.business.core.domain.accommodation.QAccommodation.accommodation;
import static com.example.airbnb.business.core.domain.accommodation.QComment.comment;
import static com.example.airbnb.business.core.domain.member.QMember.member;

@Repository
@RequiredArgsConstructor
public class CommentReadRepository {

    private final JPAQueryFactory queryFactory;

    public List<Comment> findCommentsByAccommodation(Long id) {
        return queryFactory.selectFrom(comment)
                .join(comment.member, member)
                .fetchJoin()
                .join(comment.accommodation, accommodation)
                .where(accommodation.accommodationId.eq(id))
                .fetch();
    }
}
