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
	
	//회원아이디
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="account_id")
	private List<Account> account;

	//내용
	private String content;

	//제목
	private String title;
}
