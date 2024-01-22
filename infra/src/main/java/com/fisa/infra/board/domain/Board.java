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
	
	//Board의 commentList
	@OneToMany(mappedBy = "boardId")
	private List<Comment> commentList = new ArrayList<Comment>();
	
	//Board의 pictureList
	@OneToMany(mappedBy = "boardId")
	private List<Picture> pictureList = new ArrayList<Picture>();

	//팀  - 선수
	//회원 - 댓글
	//회원 - 게시글
	//게시글 - 댓글
	//게시글 - 사진
	//  1 - N

	/*
	* DB와 다르게 객체를 직접 넣어줘야 관리가 된다.
	* 그럼 어떻게 객체를 넣어야 하는 것인가는 연관 관계 편의 메소드를 검색해보자.
	* 검색 후 드는 생각은 양방향이나 단방향일 때 객체를 넣어주는데 테이블에서 삭제한 객체와 넣어준 객체는
	* 어떻게 되는가? -> GC라는 가비지컬렉터가 사용하지 않는 객체는 알아서 정리해준다. 이게 자바와 다른 언어의 차이점.
	* */

	/*
	* 이것이 연관 관계 편의 메소드로 단방향일 때는 아래로 사용되고
	* 양방향일 때는 댓글(comment)를 보면 된다.
	* 이것을 사용할 때는 post로 가지고 온 dto로 객체를 생성한 후, 값을 넣어주면 된다.
	* 값을 넣어 줄때는 accountRepository에서 조회한 후 setAccount()로 호출하고 넣어주면 됨.
	* accountRepository에서 조회를 하지 않으면 jpa 캐시에는 값이 없기 때문에 null이 뜰것이다.
	* */
	public void setAccount(Account account) {
		this.account = account;
	}
}
