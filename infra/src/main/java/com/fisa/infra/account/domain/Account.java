package com.fisa.infra.account.domain;

import com.fisa.infra.board.domain.Board;
import com.fisa.infra.common.domain.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLRestriction;

@RequiredArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@SQLRestriction("IS_DELETED =false")
@Getter
@Builder
@Table(name = "accounts") // 데이터베이스내 예약어가 겹치지 않게 하기 위해 복수 형태로 작성합니다.
@Entity
public class Account extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_id")
	private Long accountId;

	@Column(nullable = false, unique = true)
	private String loginId;

	@Column(nullable = false)
	private String pwd;

	//이름
	private String name;

	//소속
	private String belong;

	//성별
	private boolean gender;

	//회원사진주소
	private String imageUrl;

	//스택
	private String stack;

	//포트폴리오
	private String portfolio;

	//직군
	private String job;

	@OneToMany(mappedBy = "account")
	private List<Board> board = new ArrayList<Board>();

	@Column(columnDefinition = "boolean default false")
	private boolean isDeleted;

}


