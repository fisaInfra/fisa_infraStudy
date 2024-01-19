package com.fisa.infra.comment.domain;

import java.util.List;

import com.fisa.infra.account.domain.entity.Account;
import com.fisa.infra.board.domain.Board;
import com.fisa.infra.common.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {
	
	//댓글아이디
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long commentId;
	
	//연관관계 확인 부탁드려요. 뭐가 맞을까요?
	//게시글아이디
//	@OneToMany(fetch=FetchType.LAZY)
//	@JoinColumn(name="board_id")
//	private List<Board> boardId;
	
	//게시글아이디
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id", nullable = false)
	private Board boardId;
	
	//회원아이디
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="account_id")
	private List<Account> accountId;

	//내용
	private String content;
	
	//그릅
	private int groupIndex;
	
	//계층
	private int hierarchyIndex;
	
	//순서
	private int seqIndex;
}
