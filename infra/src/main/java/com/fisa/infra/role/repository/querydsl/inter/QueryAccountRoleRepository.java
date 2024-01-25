package com.fisa.infra.role.repository.querydsl.inter;

import java.util.List;
import java.util.Optional;

import com.fisa.infra.account.domain.dto.AccountRoleDTO;
import com.fisa.infra.account.domain.dto.AccountRolesDTO;
import com.fisa.infra.role.domain.dto.RoleNameDTO;
import com.fisa.infra.role.domain.entity.AccountRole;

public interface QueryAccountRoleRepository {

	List<RoleNameDTO> findAccountRoleByLoginId(String loginId);


}
