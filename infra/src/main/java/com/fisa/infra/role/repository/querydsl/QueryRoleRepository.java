package com.fisa.infra.role.repository.querydsl;

import java.util.Optional;

import com.fisa.infra.role.domain.entity.Role;

public interface QueryRoleRepository {

	Optional<Role> findById(Long id);

}
