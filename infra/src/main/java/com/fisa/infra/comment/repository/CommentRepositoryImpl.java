package com.fisa.infra.comment.repository;

import com.fisa.infra.comment.domain.Comment;
import com.fisa.infra.comment.domain.QComment;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.fisa.infra.comment.domain.QComment.comment;

@Repository
public class CommentRepositoryImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CommentRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Comment> findByParent(Long parentId) {
        Comment fetchOne = queryFactory
                .selectFrom(comment)
                .where(comment.commentId.in(parentId))
                .leftJoin(comment.parent)
                .fetchJoin()
                .fetchOne();
        return Optional.ofNullable(fetchOne);
    }

    @Override
    public void deleteByComment(Long commentId) {
        queryFactory
                .delete(comment)
                .where(comment.commentId.eq(commentId))
                .execute();
    }


}
