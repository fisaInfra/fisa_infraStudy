package com.fisa.infra.comment.domain;

import com.fisa.infra.account.domain.Account;
import com.fisa.infra.board.domain.Board;
import com.fisa.infra.common.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter

@DynamicInsert
@DynamicUpdate

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {
	
	//댓글아이디
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "comment_id")
	private Long commentId;
	
	//게시글
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="board_id")
	private Board board;

	//회원
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="account_id")
	private Account account;

	//댓글
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Comment parent;

	//대댓글
	@OneToMany(mappedBy = "parent", orphanRemoval = true)
	private List<Comment> children = new ArrayList<>();

	//내용
	private String content;

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void setParent(Comment parent) { this.parent = parent; }

	public void setComment(Comment parent) {
		if (this.parent != null) { // 기존 댓글이 존재하면
			this.parent.getChildren().remove(this); // 관계를 끊는다.
		}
		this.parent = parent;
		parent.getChildren().add(this);
	}

	//===생성 메서드 ===//

	@Builder
	public Comment(Board board, Account account, String content) {
		this.board = board;
		this.account = account;
		this.content = content;
	}
}
