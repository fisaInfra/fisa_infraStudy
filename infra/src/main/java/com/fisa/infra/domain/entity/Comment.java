package com.fisa.infra.domain.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
public class Comment extends BaseEntity{
	
	//댓글아이디
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long commentId;
	
	//게시글아이디
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="board_id")
	private List<Board> boardId;
	
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
