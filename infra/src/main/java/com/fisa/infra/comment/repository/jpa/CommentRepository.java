package com.fisa.infra.comment.repository.jpa;

import com.fisa.infra.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*
 * 상속 클래스에만 Repsitory 애너테이션을 붙여주셔야 합니다 해당 인터페이스는 이미 Repository를 상속받고 있기 때문에 따로 하지 않아도 됨!
 * 그리고 해당 Repository로 상속 받은 이유는 정의하지 않은 많은 메서드를 JpaRepository를 사용하면 사용할 수 있게 되는데
 * 이는 문제가될 수 있기 때문에 메서드를 명시했을 때만 사용이 가능한 Repository 를 상속 받아 사용합니다.
 * 즉, 다른 말로 이곳에 인터페이스 메서드를 명시한 인터페이스를 또 하나 더 작성해서 상속 받아야한다는 말입니다.
 * */
public interface CommentRepository extends JpaRepository<Comment,Long>, CommentRepositoryCustom {
    Comment save(Comment comment);

    @Query("select c from Comment c where c.board.boardId = ?1 order by c.createdTime")
    List<Comment> findCommentByBoardId(Long boardId);

    @Query("select c from Comment c where c.parent.commentId = ?1 order by c.createdTime")
    List<Comment> findCommentByParentId(Long parentId);
}
