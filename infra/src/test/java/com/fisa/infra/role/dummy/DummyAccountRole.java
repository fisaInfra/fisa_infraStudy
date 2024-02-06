package com.fisa.infra.role.dummy;

import com.fisa.infra.account.domain.Account;
import com.fisa.infra.role.domain.entity.AccountRole;
import com.fisa.infra.role.domain.entity.Role;

public class DummyAccountRole {

	public static AccountRole dummy(Account account, Role role){

		return AccountRole.builder()
			.pk(AccountRole.Pk.builder()
				.roleId(role.getRoleId())
				.accountId(account.getAccountId())
				.build())
			.account(account)
			.role(role)
			.build();
	}
}
