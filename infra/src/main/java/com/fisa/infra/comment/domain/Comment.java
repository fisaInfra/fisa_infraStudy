package com.fisa.infra.comment.domain;

import com.fisa.infra.account.domain.Account;
import com.fisa.infra.board.domain.Board;
import com.fisa.infra.common.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;

import java.util.ArrayList;
import java.util.List;

@SQLDelete(sql = "UPDATE comment SET is")
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
	@JoinColumn(name="board")
	private Board board;

	//회원
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="account")
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

	@Column(columnDefinition = "boolean default false")
	private boolean isDeleted;


	public void addAccount(Account account) {
		this.account = account;
	}

	public void addBoard(Board board) {
		//게시글에 기존에 있던 댓글이 있던 경우
		if (this.board != null) {
			//기존 게시글에서 연관 관계를 삭제함
			this.board.getCommentList().remove(this);
		}
		this.board = board;
		board.getCommentList().add(this);
	}

	public void addtComment(Comment parent) {
		//대댓글이 이미 댓글이 등록된 경우
		if (this.parent != null) {
			//기존 댓글에서 연관 관계를 삭제함
			this.parent.getChildren().remove(this);
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

	public void updateContent() {
		this.content = "해당 댓글은 삭제되었습니다";
		this.isDeleted = true;
	}
}
