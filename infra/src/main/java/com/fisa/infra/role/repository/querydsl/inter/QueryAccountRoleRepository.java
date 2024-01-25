package com.fisa.infra.role.repository.querydsl.inter;

import java.util.Optional;

import com.fisa.infra.account.domain.dto.AccountRoleDTO;

public interface QueryAccountRoleRepository {

	Optional<AccountRoleDTO> findAccountRoleByLoginId(String loginId);
}
