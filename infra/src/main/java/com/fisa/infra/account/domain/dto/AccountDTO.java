package com.fisa.infra.account.domain.dto;

import com.fisa.infra.account.domain.Account;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Builder
public class AccountDTO {
	//key
	@Id
	private Long accountId;

	//계정아이디
	private String loginId;

	private String pwd;

	//회원사진주소
	private String imageUrl;

	//이름
	private String name;

	//소속
	private String belong;

	//성별
	private boolean gender;

	//스택
	private String stack;

	//포트폴리오
	private String portfolio;

	//직군
	private String job;

	private boolean isDeleted;
	
	public static AccountDTO fromEntity(Account account) {
		
		return AccountDTO.builder().accountId(account.getAccountId())
				.loginId(account.getLoginId()).pwd(account.getPwd()).name(account.getName()).belong(account.getBelong())
				.gender(account.isGender()).imageUrl(account.getImageUrl()).stack(account.getStack()).portfolio(account.getPortfolio())
				.job(account.getJob()).isDeleted(account.isDeleted())
				.build();
		
	}
}
