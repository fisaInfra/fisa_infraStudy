package com.fisa.infra.role.repository.querydsl.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.fisa.infra.account.domain.dto.AccountRoleDTO;
import com.fisa.infra.role.domain.entity.QAccountRole;
import com.fisa.infra.role.repository.querydsl.inter.QueryAccountRoleRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QueryAccountRoleRoleRepositoryImpl implements QueryAccountRoleRepository {

	private final JPAQueryFactory factory;

	@Override
	public Optional<AccountRoleDTO> findAccountRoleByLoginId(String loginId) {

		QAccountRole accountRole = QAccountRole.accountRole;


		return Optional.ofNullable(factory
			.select(Projections.constructor(AccountRoleDTO.class,
				accountRole.account.accountId,
				accountRole.account.name.as("accountName"),
				accountRole.account.loginId,
				accountRole.account.pwd,
				accountRole.role.name.as("roleName")
				))
			.where(accountRole.account.loginId.eq(loginId)).fetchOne());
	}
}
