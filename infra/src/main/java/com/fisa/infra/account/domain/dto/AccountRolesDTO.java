package com.fisa.infra.account.domain.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRolesDTO {

	private Long accountId;
	private String accountName;
	private String loginId;
	private String pwd;
	private boolean isDeleted;
	private List<String> roleName;

}
