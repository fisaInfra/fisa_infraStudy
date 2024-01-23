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

	/*
	* jpa는 객체 개념으로 설계를 하기 때문에 데이터베이스의 FK와 다르다.
	* 테이블은 엔티티고 테이블 내부의 FK는 객체로 들어가서 사용된다.
	*
	* 현재 단방향으로 맵핑 했기 때문에 Board에서 account는 조회가 되나 account에서 Board는 조회가 되지 않음.
	* 그러면 account에서 Board로 조회하려면? 양방향 맵핑으로 변경하면 된다.
	* 양뱡향과 단방향에서 뭐가 좋냐는 질문에는 양방향으로 하면 객체 관리가 어렵기 하지만 조회가 편하다는 장점이 있음
	* 결론은 비즈니스 로직과 상황에 맞게 사용하면 됨.
	* */

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
	
	@OneToMany(mappedBy = "board")
	private List<Comment> commentList = new ArrayList<Comment>();
	
	//Board의 pictureList
	@OneToMany(mappedBy = "board")
	private List<Picture> pictureList = new ArrayList<Picture>();


	public void setAccount(Account account) {
		this.account = account;
	}
}
