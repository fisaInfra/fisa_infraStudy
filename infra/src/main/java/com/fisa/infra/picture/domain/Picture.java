package com.fisa.infra.picture.domain;

import java.util.List;

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
	@Column(name = "picture_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long pictureId;
	
	//게시글아이디
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="board_id")
	private List<Board> boardId;

	//사진파일주소
	private String pictureFileUrl;
	
	//사진주소
	private String pictureUrl;
	
}
