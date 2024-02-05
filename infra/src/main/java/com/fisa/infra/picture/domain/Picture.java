package com.fisa.infra.picture.domain;

//import는 안쓸때마다 정리해주는 것이 좋음.
import com.fisa.infra.account.domain.Account;
import com.fisa.infra.board.domain.Board;
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
@Table(name = "pictures")
public class Picture {

	//사진아이디
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "picture_id")
	private Long pictureId;
	
	//게시글
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="board_id")
	private Board board;

	//사진파일주소
	//파일 저장 경로
	private String pictureFileUrl;
	
	//사진주소
	//웹에서 접근 가능한 URL
	private String pictureUrl;

	public void addBoard(Board board) {
		//게시글에 기존에 있던 댓글이 있던 경우
		if (this.board != null) {
			//기존 게시글에서 연관 관계를 삭제함
			this.board.getPictureList().remove(this);
		}
		this.board = board;
		board.getPictureList().add(this);
	}

	public static Picture savePicture(String pictureFileUrl, String pictureUrl){
		Picture picture = new Picture();
		picture.pictureFileUrl = pictureFileUrl;
		picture.pictureUrl = pictureUrl;
		return picture;
	}
}
