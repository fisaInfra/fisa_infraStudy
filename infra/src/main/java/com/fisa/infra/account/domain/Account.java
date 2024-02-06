package com.fisa.infra.account.domain;

import com.fisa.infra.board.domain.Board;
import com.fisa.infra.common.domain.entity.BaseEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


/*
* @setter같은 경우엔 사용하지 않는 것이 좋습니다!.
* 해당 엔티티에 여러 과정에 있어서 setter 때문에 데이터 손실, 변경이될 위험이 있기 때문입니다!
* */

@RequiredArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@SQLRestriction("IS_DELETED =false")
@Getter
@Builder
@Table(name = "accounts") // 데이터베이스내 예약어가 겹치지 않게 하기 위해 복수 형태로 작성합니다.
@Entity
@SQLDelete(sql = "UPDATE account set is_deleted = true WHERE login_id = ?")
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

	@Builder.Default
	@OneToMany(mappedBy = "account")
	private List<Board> board = new ArrayList<Board>();

	@Column(columnDefinition = "boolean default false")
	private boolean isDeleted;
	
	public static Account createAccount(String loginId, String pwd, String name, String belong, boolean gender, String imageUrl, String stack, String portfolio, String job, boolean isDeleted) {
		return Account.builder()
				.loginId(loginId).pwd(pwd).name(name).belong(belong)
				.gender(gender).imageUrl(imageUrl).stack(stack).portfolio(portfolio)
				.job(job).isDeleted(isDeleted)
				.build();
	}

	public Account deletedAccount(String loginId) {
		this.loginId = "deleted" + loginId;
		setIsDeleted(true);
		return this;
	}
}


