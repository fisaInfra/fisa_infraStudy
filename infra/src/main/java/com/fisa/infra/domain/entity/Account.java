package com.fisa.infra.domain.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLRestriction;


/*
* @setter같은 경우엔 사용하지 않는 것이 좋습니다!.
* 해당 엔티티에 여러 과정에 있어서 setter 때문에 데이터 손실, 변경이될 위험이 있기 때문입니다!
* */


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@DynamicInsert
@DynamicUpdate
@Getter
@Builder
@SQLRestriction("IS_DELETED =false")
@Table(name = "accounts") // 데이터베이스내 예약어가 겹치지 않게 하기 위해 복수 형태로 작성합니다.
@Entity
public class Account extends BaseEntity{


	/*
	* pk 값은 String 타입으로 선언하게 되면 검색 시 성능 저하의 이슈가 있을 수 있어요! 그래서 간단하게 다들 숫자 타입을 사용합니다.
	*
	* pk 생성 시 @GeneratedValue를 사용해서 선택해주셔야 하는데 이때는 데이터베이스 종류에 따라 달라요!
	* */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_id")
	private Long accountId;

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


	/**
	 *조인 컬럼할 때는 entity의 필드명이 아닌 데이터베이스 내의 컬럼명으로 작성해주셔야 합니다!
	 * 이때 저희는 mysql에서 사용하고 있는 스네이크 문법을 사용해 컬럼을 집어넣고 있습니다!>
	 * */
	@JoinColumn(name = "role_id")
	@OneToMany(fetch = FetchType.LAZY)
	private List<Role> role;

	//직군
	private String job;

}


