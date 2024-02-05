package com.fisa.infra.board.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.fisa.infra.comment.domain.Comment;
import com.fisa.infra.account.domain.Account;
import com.fisa.infra.common.domain.entity.BaseEntity;
import com.fisa.infra.picture.domain.Picture;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@DynamicUpdate
@DynamicInsert
@Builder
@Entity
@Table(name = "boards")
public class Board extends BaseEntity {

	//게시글아이디
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "board_id")
	private Long boardId;
	
	//회원 객체
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="account_id")
	private Account account;

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

	private Long viewCount = 0L;

	@Builder.Default
	@OneToMany(mappedBy = "board")
	private List<Comment> commentList = new ArrayList<>();
	
	//Board의 pictureList
	@Builder.Default
	@OneToMany(mappedBy = "board")
	private List<Picture> pictureList = new ArrayList<>();

	public void addAccount(Account account) {
		this.account = account;
	}

}
