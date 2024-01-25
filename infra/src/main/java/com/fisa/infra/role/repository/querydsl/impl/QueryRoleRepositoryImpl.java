package com.fisa.infra.role.repository.querydsl.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.fisa.infra.role.domain.entity.QRole;
import com.fisa.infra.role.domain.entity.Role;
import com.fisa.infra.role.repository.querydsl.inter.QueryRoleRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class QueryRoleRepositoryImpl implements QueryRoleRepository {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Optional<Role> findById(Long id) {
		QRole role = QRole.role;

		return Optional.ofNullable(
			jpaQueryFactory
				.selectFrom(role)
				.where(role.roleId.eq(id))
				.fetchOne()
		);
	}
}
