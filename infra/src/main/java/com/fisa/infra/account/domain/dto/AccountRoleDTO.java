package com.fisa.infra.account.domain.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRoleDTO {

	private Long accountId;
	private String accountName;
	private String loginId;
	private String pwd;


	private String roleName;

}
