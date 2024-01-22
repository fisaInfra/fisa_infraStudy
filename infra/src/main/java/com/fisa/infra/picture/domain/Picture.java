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
<<<<<<< HEAD:infra/src/main/java/com/fisa/infra/domain/entity/Picture.java
=======
	@Column(name = "picture_id")
>>>>>>> a58090915abafbaf9d5b211ed2255409967480bb:infra/src/main/java/com/fisa/infra/picture/domain/Picture.java
	private Long pictureId;
	
	//게시글
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="board_id")
	private Board board;

	//사진파일주소
	private String pictureFileUrl;
	
	//사진주소
	private String pictureUrl;

	public void setBoard(Board board) {
		this.board = board;
	}
}
