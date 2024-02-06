package com.fisa.infra.role.repository.querydsl;

import java.util.Optional;

import com.fisa.infra.role.domain.entity.Role;

public interface RoleRepository {
	Optional<Role> findById(Long id);
}
