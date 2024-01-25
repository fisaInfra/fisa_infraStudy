package com.fisa.infra.role.repository.querydsl.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.fisa.infra.account.domain.dto.AccountRoleDTO;
import com.fisa.infra.role.domain.dto.RoleNameDTO;
import com.fisa.infra.role.domain.entity.AccountRole;
import com.fisa.infra.role.domain.entity.QAccountRole;
import com.fisa.infra.role.repository.querydsl.inter.QueryAccountRoleRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QueryAccountRoleRepositoryImpl implements QueryAccountRoleRepository {

	private final JPAQueryFactory factory;

	@Override
	public List<RoleNameDTO> findAccountRoleByLoginId(String loginId) {

		QAccountRole accountRole = QAccountRole.accountRole;

		return factory
			.select(Projections.constructor(RoleNameDTO.class,accountRole.role.name.as("roleName")))
			.from(accountRole)
			.where(accountRole.account.loginId.eq(loginId)).fetch();

	}


}
