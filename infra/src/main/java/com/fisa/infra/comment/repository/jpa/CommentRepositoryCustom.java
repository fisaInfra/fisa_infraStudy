package com.fisa.infra.comment.repository.jpa;

import com.fisa.infra.comment.domain.Comment;

import java.util.Optional;

public interface CommentRepositoryCustom {

    Optional<Comment> findByParent(Long parentId);

    void deleteByComment(Long commentId);
}
