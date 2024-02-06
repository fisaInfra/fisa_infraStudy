package com.fisa.infra.account.domain;

import com.fisa.infra.account.domain.dto.AccountDTO;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.fisa.infra.common.domain.entity.BaseEntity;

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


	/*
	* pk 값은 String 타입으로 선언하게 되면 검색 시 성능 저하의 이슈가 있을 수 있어요! 그래서 간단하게 다들 숫자 타입을 사용합니다.
	*
	* pk 생성 시 @GeneratedValue를 사용해서 선택해주셔야 하는데 이때는 데이터베이스 종류에 따라 달라요!
	*
	* --김어진
	* 면접에서 Long vs int pk를 질문하는 곳도 있으니 시간날 때 정리 ㄱㄱ
	* */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_id")
	private Long accountId;

	@Column(nullable = false, unique = true)
	private String loginId;
	//id

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

	@Column(columnDefinition = "boolean default false")
	private boolean isDeleted;
	
	public static Account createAccount(String loginId, String pwd, String name, String belong, boolean gender, String imageUrl, String stack, String portfolio, String job, boolean isDeleted) {
		return Account.builder()
				.loginId(loginId).pwd(pwd).name(name).belong(belong)
				.gender(gender).imageUrl(imageUrl).stack(stack).portfolio(portfolio)
				.job(job).isDeleted(isDeleted)
				.build();
	}

	public void updateAccount(AccountDTO accountDTO) {
		this.name = accountDTO.getLoginId();
		this.pwd = accountDTO.getPwd();
		this.name = accountDTO.getName();
		this.belong = accountDTO.getBelong();
		this.imageUrl = accountDTO.getImageUrl();
		this.stack = accountDTO.getStack();
		this.portfolio = accountDTO.getPortfolio();
		this.job = accountDTO.getJob();
	}
	
	public Account deletedAccount(String loginId) {
		this.loginId = "deleted" + loginId;
		setIsDeleted(true);
		return this;
	}


}


