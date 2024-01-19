package com.fisa.infra.board.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fisa.infra.account.domain.entity.Account;
import com.fisa.infra.comment.domain.Comment;
import com.fisa.infra.common.domain.entity.BaseEntity;
import com.fisa.infra.picture.domain.Picture;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "boards")
public class Board extends BaseEntity {

	//게시글아이디
	@Id
	@Column(name = "board_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long boardId;
	
	//연관관계 확인 부탁드려요. 뭐가 맞을까요?
	//회원아이디
//	@OneToMany(fetch=FetchType.LAZY)
//	@JoinColumn(name="account_id")
//	private List<Account> account;
	
	//회원아이디
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", nullable = false)
	private Account accountId;

	//내용
	private String content;

	//제목
	private String title;
	
	//등록일자
	@Column(name = "upload_at")
	private Date uploadAt;
	
	//생성날짜
	@Column(name = "created_at")
	private Date createdAt;
	
	//수정날짜
	@Column(name = "updated_at")
	private Date updatedAt;
	
	//삭제여부
	@Column(name = "is_deleted")
	private boolean isDeleted;
	
	//Board의 commentList
	@OneToMany(mappedBy = "boardId")
	private List<Comment> commentList = new ArrayList<Comment>();
	
	//Board의 pictureList
	@OneToMany(mappedBy = "boardId")
	private List<Picture> pictureList = new ArrayList<Picture>();
}
