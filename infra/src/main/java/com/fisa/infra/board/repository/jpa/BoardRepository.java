package com.fisa.infra.board.repository.jpa;

import com.fisa.infra.board.domain.Board;
import com.fisa.infra.board.repository.basic.CommonBoardRepository;
import com.fisa.infra.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {

//	Board findBoardByLoginId(String loginId);

	@Query("select c from Comment c where c.board.boardId = ?1 order by c.createdTime")
	List<Comment> findCommentByBoardId(Long boardId);
}
